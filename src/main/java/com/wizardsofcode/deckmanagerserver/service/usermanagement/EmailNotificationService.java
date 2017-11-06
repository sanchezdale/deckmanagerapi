/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/9/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivation;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;

public interface EmailNotificationService {

    boolean sendActivationMessage(UserActivation act);

    boolean sendResetPasswordMessage(User user, UserPasswordReset passwordReset);

    boolean sendActivatedMessage(User user);



}
