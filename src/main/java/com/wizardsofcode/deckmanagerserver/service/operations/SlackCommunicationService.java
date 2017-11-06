/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.operations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizardsofcode.deckmanagerserver.model.operations.ServerSetting;
import com.wizardsofcode.deckmanagerserver.model.operations.SlackClientDetails;
import com.wizardsofcode.deckmanagerserver.model.operations.SlackClientsDAO;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class SlackCommunicationService implements HealthIndicator{

    private ServerSettingsService settingsService;

    private SlackClientsDAO clientsDAO;

    private static Logger logger = LoggerFactory.getLogger(SlackCommunicationService.class);

    @Autowired
    public SlackCommunicationService(ServerSettingsService settingsService, SlackClientsDAO clientsDAO){
        this.clientsDAO = clientsDAO;
        this.settingsService = settingsService;
    }


    public SlackResponse getEmailServiceStatus(){

        if(settingsService.retrieveSetting("EMAIL_ACTIVE").getValue().equalsIgnoreCase("true")){
            SlackResponse response = new SlackResponse();
            response.text = "The Status of the Email service is:";
            response.attachments.add(new SlackAttachment("Enabled", "#36a64f"));
            return response;
        }else{
            SlackResponse response = new SlackResponse();
            response.text = "The Status of the Email service is:";
            response.attachments.add(new SlackAttachment("Disabled", "#FF0000"));
            return response;
        }
    }

    public SlackResponse updateEmailService(boolean value){
        settingsService.updateEmailServerStatus(value);
        SlackResponse response = new SlackResponse();
        response.text = "The new Status of the Email service is:";
        if(value)
            response.attachments.add(new SlackAttachment("Enabled", "#36a64f"));
        else
            response.attachments.add(new SlackAttachment("Disable", "#FF0000"));

        return response;
    }

    public SlackResponse checkHealth(){

        SlackResponse response = new SlackResponse();
        if(settingsService.retrieveSetting("EMAIL_ACTIVE").getValue().equalsIgnoreCase("true")){
            response.text = "Server Status:";

            response.attachments.add(new SlackAttachment("UP","#36a64f"));
            response.attachments.add(new SlackAttachment("Email Sevice is Enabled","#36a64f"));

        }else if(settingsService.retrieveSetting("DEPLOYING").getValue().equalsIgnoreCase("true")){
            response.text = "Server Status:";
            response.attachments.add(new SlackAttachment("Deploying","#FF0000"));
        }else{
            response.text = "Server Status:";
            response.attachments.add(new SlackAttachment("UP","#36a64f"));

            if(settingsService.retrieveSetting("EMAIL_ACTIVE").getValue().equalsIgnoreCase("true")){
                response.attachments.add(new SlackAttachment("Email Server is Enabled", "#36a64f"));
            }else{
                response.attachments.add(new SlackAttachment("Email Server is Disabled", "#FF0000"));
            }
        }
        return response;
    }

    public SlackResponse errorInRequest(String error){
        SlackResponse response = new SlackResponse();
        response.text = "The is an error with your request";
        response.attachments.add(new SlackAttachment(error, "#FF0000"));
        return response;
    }

    public SlackResponse performauth(String code){
        logger.debug("performAuth() - code: " +  code);
        ServerSetting slackAuth = settingsService.retrieveSetting("SLACK_AUTH");
        ServerSetting slackClient = settingsService.retrieveSetting("SLACK_CLIENT");
        ServerSetting slackSecret = settingsService.retrieveSetting("SLACK_SECRET");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", slackClient.getValue());
        map.add("client_secret",slackSecret.getValue());
        map.add("code", code);
        map.add("redirect_ur","https://slack.com/");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<SlackClientDetails> response = restTemplate.postForEntity( slackAuth.getValue(),
                request , SlackClientDetails.class );
        logger.debug("performAuth() - response: " +  response);
        if(response.getStatusCode() == HttpStatus.OK)
            clientsDAO.save(response.getBody());

        return null;
    }

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    public int check(){
        return 1;
    }


    @JsonInclude(JsonInclude.Include.ALWAYS)
    public class SlackResponse{
        @JsonProperty
        private String text;
        @JsonProperty
        private List<SlackAttachment> attachments;

        SlackResponse(){
            attachments = new ArrayList<>();
        }


    }

    public class SlackAttachment{
        @JsonProperty
        private String text;
        @JsonProperty
        private String color;

        public SlackAttachment(String text, String color) {
            this.text = text;
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}


