/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

    PasswordSecurityLevel level;
    String severExpression;
    String relaxedExpression;

    @Override
    public void initialize(Password constraintAnnotation) {

        level = constraintAnnotation.value();

        // between 6 and 15, at least one uppercase, one lowercase and one number
        severExpression = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$";

        // length between 4 and 8, at least one number
        relaxedExpression = "^(?=.*\\d).{4,8}$";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        return (level.equals(PasswordSecurityLevel.SEVERE) ?
                value.matches(severExpression) : value.matches(relaxedExpression));
    }
}
