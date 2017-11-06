/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/13/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivation;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.NonValidActivationPinException;

import java.util.UUID;

public interface UserActivationService {


    UserActivation createUserActivation(User u);

    boolean activateUser(UUID u) throws NonValidActivationPinException;
}
