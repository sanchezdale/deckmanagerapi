/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/19/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.validation;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UserParameterNotValidException;

public class UserValidator {

    public UserValidator() {

    }

    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }


    public static void validate(Object target) throws UserParameterNotValidException {
        User user = (User) target;

        //Check nullity or emptiness of required
        if ((user.getUsername() == null) || user.getUsername().length() < 1) {
            throw new UserParameterNotValidException("Username is Null or is empty");
        }

        if ((user.getFirstName() == null) || user.getFirstName().length() < 1) {
            throw new UserParameterNotValidException("First name is Null or empty");
        }

    }
}
