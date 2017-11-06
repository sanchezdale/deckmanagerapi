/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.wizardsofcode.deckmanagerserver.model.communication.UserEmailTemplate;
import com.wizardsofcode.deckmanagerserver.model.communication.UserEmailTemplateDAO;
import com.wizardsofcode.deckmanagerserver.model.operations.ServerSettingDAO;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivation;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserEmail;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class EmailNotificationServiceImplAws implements EmailNotificationService {

    Environment env;
    private ServerSettingDAO serverSettings;

    private UserEmailTemplateDAO emailTemplateDAO;

    private AmazonSimpleEmailService emailService;

    private static HashMap<String,String> parameters = new HashMap<>();


    @Autowired
    public EmailNotificationServiceImplAws(ServerSettingDAO serverSettingDAO, UserEmailTemplateDAO emailTemplate,
                                           AmazonSimpleEmailService emailService){
        this.serverSettings = serverSettingDAO;
        this.emailTemplateDAO = emailTemplate;
        this.emailService = emailService;
    }


    @Override
    public boolean sendActivationMessage(UserActivation act){
        String serverUrl = serverSettings.findBySettingName("SERVER_URL").getValue();
        parameters.put("ACTIVATION_URL", serverUrl + "activate/" +act.getActivationId());
        parameters.put("NAME", act.getUser().getFirstName());
        return createEmailTemplate("ACTIVATION_TEMPLATE", act.getUser().getEmail().getEmail());
    }

    @Override
    public boolean sendResetPasswordMessage(User user, UserPasswordReset passwordReset) {
        String serverUrl = serverSettings.findBySettingName("SERVER_URL").getValue();
        parameters.put("CLIENT_URL",  "The client website + the credential: " + passwordReset.getPasswordResetID().toString());
        parameters.put("NAME", user.getFirstName());
        return createEmailTemplate("RESET_PASSWORD_TEMPLATE", user.getEmail().getEmail());
    }


    public boolean sendEmail(String subject, String content, String email) {

        if (serverSettings.findBySettingName("EMAIL_ACTIVE").getValue().equals("true")) {

            Destination destination = new Destination().withToAddresses(email);
            Content emailContent = new Content().withCharset("UTF-8").withData(content);
            Body emailbody = new Body().withHtml(emailContent);

            Message message = new Message().withBody(emailbody)
                    .withSubject(new Content().withCharset("UTF-8")
                            .withData(subject));

            SendEmailRequest emailRequest = new SendEmailRequest()
                    .withDestination(destination)
                    .withMessage(message)
                    .withSource(serverSettings.findBySettingName("SERVER_EMAIL").getValue());

            try{
                emailService.sendEmail(emailRequest);
            }catch (Exception e){
                return false;
            }
            parameters.clear();
        }

        return true;
    }

    @Override
    public boolean sendActivatedMessage(User user) {
        return false;
    }


    private boolean createEmailTemplate(String templateName, String email){
        UserEmailTemplate emailTemplate = emailTemplateDAO.findByName(templateName);
        UserEmailTemplate sendingTemplate = new UserEmailTemplate(emailTemplate);
        sendingTemplate.runMapper(parameters);
        sendEmail(sendingTemplate.getSubject(),sendingTemplate.getContent(),email);
        return true;
    }


}
