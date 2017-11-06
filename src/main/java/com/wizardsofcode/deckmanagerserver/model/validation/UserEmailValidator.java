/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/19/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.validation;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserEmail;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UserParameterNotValidException;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserEmailValidator{

    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
            "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
            "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-" +
            "\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    public boolean supports(Class<?> clazz) {
        return UserEmail.class.equals(clazz);
    }


    public static void validate(Object target) throws UserParameterNotValidException {
        UserEmail email = (UserEmail) target;

        if(target == null)
            throw new UserParameterNotValidException("Email cannot be null");

        if (email.getEmail() == null) {
            throw new UserParameterNotValidException("Email cannot be null");
        } else {
            if (email.getEmail().isEmpty() || (email.getEmail() == null)) {
                throw new UserParameterNotValidException("Email is Null or empty");
            }else if(!email.getEmail().matches(emailRegex)){
                throw new UserParameterNotValidException("Email is not Valid");
            }
        }
    }
}
