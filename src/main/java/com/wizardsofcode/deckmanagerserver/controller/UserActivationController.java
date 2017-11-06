/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import com.wizardsofcode.deckmanagerserver.model.operations.RestResponse;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.NonValidActivationPinException;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserActivationService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/activate")
public class UserActivationController {


    private UserActivationService activationService;

    private UserManagementService userManagementService;

    private static final Logger logger = LoggerFactory.getLogger(UserActivationController.class);

    @Autowired
    public UserActivationController(UserActivationService activationService,
                                    UserManagementService userManagementService){
        this.activationService = activationService;
        this.userManagementService = userManagementService;
    }


    @CrossOrigin
    @RequestMapping(value = "/{activationId}", method = RequestMethod.GET)
    public ResponseEntity<?> doActivation(@PathVariable UUID activationId)throws Exception{

        logger.debug("Method: doActivation()");
        activationService.activateUser(activationId);
        return new ResponseEntity<>(new RestResponse("null", "The User was succesfully activated",
                HttpStatus.OK), HttpStatus.OK);
    }
}
