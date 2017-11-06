/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;


import com.wizardsofcode.deckmanagerserver.model.operations.SlackClientDetails;
import com.wizardsofcode.deckmanagerserver.model.operations.SlackCommand;
import com.wizardsofcode.deckmanagerserver.service.operations.SlackCommunicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/slack")
public class SlackController {

    private static final Logger logger = LoggerFactory.getLogger(SlackController.class);

    private SlackCommunicationService slackCommunicationService;

    @Autowired
    public SlackController(SlackCommunicationService communicationService){
        this.slackCommunicationService = communicationService;
    }


    @RequestMapping(value = "/emailservice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
    public ResponseEntity<?> emailServiceInteraction(@RequestBody SlackCommand command){

        SlackCommunicationService.SlackResponse response;

        logger.debug("Method: emailServiceInteraction() - " + command);

        if(command.getText().equalsIgnoreCase("check")){
            response = slackCommunicationService.getEmailServiceStatus();
        }else if(command.getText().equalsIgnoreCase("enabled")){
            response = slackCommunicationService.updateEmailService(true);
        }else if(command.getText().equalsIgnoreCase("disabled")){
            response = slackCommunicationService.updateEmailService(false);
        }else
            response = slackCommunicationService.errorInRequest("Command not recognized");

        logger.debug("Method: emailServiceInteraction() - " + response);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/health", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
    public ResponseEntity<?> applicationHealth(@RequestBody SlackCommand command){
        SlackCommunicationService.SlackResponse response;
        logger.debug("Method: applicationHealth() - " + command);
        response = slackCommunicationService.checkHealth();
        logger.debug("Method: emailServiceInteraction() - " + response);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> addClient(@RequestParam String code){
        logger.debug("Method: addClient() - " + code);

        if(slackCommunicationService.performauth(code) != null){
            logger.debug("Method: addClient() - response 200");
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            logger.debug("Method: addClient() - response 200");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addClient(@RequestBody SlackClientDetails code){

        logger.debug(code.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
