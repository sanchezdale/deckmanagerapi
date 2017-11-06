/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivation;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserEmail;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserManagementService {


    User registerUser(User newUser);

    User updateUser(User user);

    User disableUser(User user);

    User updateUserRole(User user);

    User updateUserAvatar(User user);

    User updatePasswordFromReset(UserPasswordReset passwordReset);

    User updatePasswordFromUserRequest(String oldPassword, String newPassword, String principalUsername) throws UnauthorizedUserException;

    User resetPassword(User user, UserPasswordReset resetToken);

    User retrieveUserByUsername(String username);

    User saveUser(User user);

    User retrieveUserById(long id);

    boolean checkIfUserExists(String username);

    boolean checkIfEmailExists(String email);


}
