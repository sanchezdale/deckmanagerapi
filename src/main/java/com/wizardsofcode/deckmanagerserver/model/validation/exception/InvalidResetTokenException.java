package com.wizardsofcode.deckmanagerserver.model.validation.exception;

public class InvalidResetTokenException extends Exception {

    public InvalidResetTokenException(String message) {
        super(message);
    }

    public InvalidResetTokenException() {
        super("The Token supplied is not valid, Was already used, or it expired");
    }
}
