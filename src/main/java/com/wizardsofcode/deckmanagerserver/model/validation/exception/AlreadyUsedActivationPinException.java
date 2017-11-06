/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.validation.exception;

public class AlreadyUsedActivationPinException extends Exception {


    public AlreadyUsedActivationPinException(String message) {

        super(message);
    }

    public AlreadyUsedActivationPinException() {
        super("This activation pin has been used");
    }
}
