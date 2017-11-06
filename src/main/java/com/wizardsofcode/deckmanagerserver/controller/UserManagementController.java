/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import com.wizardsofcode.deckmanagerserver.model.operations.PasswordUpdateTransporter;
import com.wizardsofcode.deckmanagerserver.model.operations.RestResponse;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserAvatar;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;
import com.wizardsofcode.deckmanagerserver.model.validation.UserEmailValidator;
import com.wizardsofcode.deckmanagerserver.model.validation.UserValidator;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.InvalidResetTokenException;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UserParameterNotValidException;
import com.wizardsofcode.deckmanagerserver.service.userinventory.UserInventoryService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserAvatarService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserManagementService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserPasswordResetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserManagementController {

    private UserManagementService userManagementService;

    private UserPasswordResetService passwordResetService;

    private UserAvatarService avatarService;

    private UserInventoryService inventoryService;

    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    public UserManagementController(UserManagementService userManagementService,
                                    UserPasswordResetService passwordResetService, UserAvatarService avatarService,
                                    UserInventoryService inventoryService){
        this.userManagementService = userManagementService;
        this.passwordResetService = passwordResetService;
        this.avatarService = avatarService;
        this.inventoryService = inventoryService;
    }

    @CrossOrigin
    @RequestMapping(value = "/profile")
    public ResponseEntity<?> getProfile(Principal principal){
        logger.debug("getProfile - " + principal.getName());
        User user = userManagementService.retrieveUserByUsername(principal.getName());

        if (user != null) {
            user.getPhoneNumber();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            logger.debug("User Not Found: " + principal.getName());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{

        logger.debug("Method: registerUser() - " + user.getUsername());
        UserValidator.validate(user);
        UserEmailValidator.validate(user.getEmail());

        if(user.getPassword() == null) {
            throw new UserParameterNotValidException("Password cannot be null");
        }
        if (userManagementService.checkIfUserExists(user.getUsername())) {
            throw new UserParameterNotValidException("Username already exists");
        } else if (userManagementService.checkIfEmailExists(user.getEmail().getEmail())) {
            throw new UserParameterNotValidException("Email is already registered to someone else");
        }

        user = userManagementService.registerUser(user);
        logger.debug("Method: registerUser() - Response 200 registered with id: " + user.getUserId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/reset/{resetId}", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@PathVariable UUID resetId, @RequestBody UserPasswordReset passwordReset) throws InvalidResetTokenException{

        //TODO: Check if user is active
        logger.debug("Method: resetPassword() - " + resetId);
        String tempPass = passwordReset.getPassword();

        passwordReset = passwordResetService.retrievePasswordRequest(resetId);
        passwordReset.setPassword(tempPass);
        passwordResetService.resetPassword(passwordReset);
        logger.debug("Method: resetPassword() - Response 200");
        return new ResponseEntity<>(new RestResponse(null, "Password Successfully Reset", HttpStatus.OK), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/update/password", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateTransporter passwordUpdateTransporter, Principal principal){

        logger.debug("Method: updatePassword() - " + principal.getName());
        User user = userManagementService.updatePasswordFromUserRequest(passwordUpdateTransporter.getOldPassword()
            ,passwordUpdateTransporter.getNewPassword(),principal.getName());

        if(user == null)
            throw new UnauthorizedUserException("User not found");
        logger.debug("Method: updatePassword() - Response 200");
        return new ResponseEntity<Object>(new RestResponse(null,"Password updated",
                HttpStatus.OK), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/resetpassword/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> createResetTokenRequest(@PathVariable String username) {

        logger.debug("Method: createResetTokenRequest() - username: " + username );
        User user = userManagementService.retrieveUserByUsername(username);

        if (user == null) {
            logger.debug("Method: createResetTokenRequest() - UserNotFound Response BAD_REQUEST");
            return new ResponseEntity<>(new RestResponse(null, "User not found",
                    HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        if(passwordResetService.checkIfExists(user)){
            logger.debug("Method: createResetTokenRequest() - PasswordRequestExists");
            return new ResponseEntity<>(new RestResponse("InvalidPasswordRequest", "There is already a password request for this user",
                    HttpStatus.CONFLICT), HttpStatus.CONFLICT);
        }
        logger.debug("Method: createResetTokenRequest() - Response 200");
        UserPasswordReset passwordReset = passwordResetService.createPasswordresetRequest(user);

        return new ResponseEntity<>(passwordReset, HttpStatus.OK);
    }


    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user, Principal principal) throws Exception{

        logger.debug("Method: updateUser() - user: " + user.toString());
        UserValidator.validate(user);

        User persisted = userManagementService.retrieveUserByUsername(user.getUsername());

        if(user.getPassword() != null)
            user.setPassword(null);

        if(principal.getName().equals(user.getUsername())) {
            user = userManagementService.updateUser(user);
        }else{
            logger.debug("Method: updateUser() - AuthorizationDataMismatch expected: " + principal.getName() + " given: "
             + user.getUsername());
            return new ResponseEntity<Object>(new RestResponse("AuthorizationDataMismatch",
                    "The updated user does not match authorization provided", HttpStatus.UNAUTHORIZED),
                    HttpStatus.UNAUTHORIZED);
        }
        logger.debug("Method: updateUser() - Response 200");

        return  new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/avatar/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file, Principal principal){

        User usr = userManagementService.retrieveUserByUsername(principal.getName());
        UserAvatar result;

        if(usr == null)
            return new ResponseEntity<Object>(new RestResponse("UserNotFoundException","User not Found", HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);

        if(file.getContentType().contains("image")){
            try {
                result = avatarService.saveOrUpdateFile(file, usr);
            }catch (IOException io){
                return new ResponseEntity<Object>(new RestResponse(io.getClass().getName(),
                        "There was a problem uploading your file.",HttpStatus.INTERNAL_SERVER_ERROR),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<Object>(new RestResponse("NotImageType",
                    "Your file does not seem to be and image",HttpStatus.BAD_REQUEST),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(result,HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{username}")
    public ResponseEntity<?> getProfileByUsername(@PathVariable String username){
        User user = userManagementService.retrieveUserByUsername(username);

        if (user != null) {
            user.setEmail(null);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            logger.debug("User Not Found: " + username);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
