/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserEmail;

public interface UserEmailService {

    UserEmail getUserEmail(User user);

    void saveUserEmail(UserEmail user);
}
