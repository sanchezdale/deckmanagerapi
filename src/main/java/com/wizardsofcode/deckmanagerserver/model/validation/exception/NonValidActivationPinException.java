/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.validation.exception;

public class NonValidActivationPinException extends Exception {


    public NonValidActivationPinException(String message) {

        super(message);
    }

    public NonValidActivationPinException() {
        super("This activation pin is invalid");
    }

}
