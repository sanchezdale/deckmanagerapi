/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/23/17                                 
 */

package com.wizardsofcode.deckmanagerserver.config;

import com.wizardsofcode.deckmanagerserver.model.operations.RestResponse;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.InvalidResetTokenException;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.NonValidActivationPinException;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UserParameterNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.OperationNotSupportedException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityHandler.class);

    @ExceptionHandler(value = {UserParameterNotValidException.class, ConstraintViolationException.class,
            InvalidResetTokenException.class, OperationNotSupportedException.class, NonValidActivationPinException.class})
    public ResponseEntity<?> handleInvalidArguments(Exception e){
        logger.error("requestId: " + MDC.get("requestId") + ": " +  e.getLocalizedMessage(),e);
       ResponseEntity response = new ResponseEntity<Object>(new RestResponse(UserParameterNotValidException.class.getName(),
                e.getMessage(), HttpStatus.CONFLICT,MDC.get("requestId")), HttpStatus.CONFLICT);
       return response;
    }


    @ExceptionHandler(value = {UnauthorizedUserException.class})
    public ResponseEntity<?> handleNotFound(Exception e){
        logger.error("requestId: " + MDC.get("requestId") + ": " +  e.getLocalizedMessage(),e);
        return new ResponseEntity<Object>(new RestResponse(UserParameterNotValidException.class.getName(),
                e.getMessage(), HttpStatus.UNAUTHORIZED, MDC.get("requestId")), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleAll(Exception e){
        logger.error("requestId: " + MDC.get("requestId") + ": " +  e.getLocalizedMessage(),e);
        return new ResponseEntity<Object>(new RestResponse(e.getClass().getName(),
                e.getMessage(), HttpStatus.CONFLICT, MDC.get("requestId")), HttpStatus.CONFLICT);
    }

}
