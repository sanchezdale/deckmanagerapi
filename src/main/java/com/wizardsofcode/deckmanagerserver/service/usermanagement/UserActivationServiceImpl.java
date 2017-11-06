/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/13/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeckDAO;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivation;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserActivationDAO;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserDAO;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.NonValidActivationPinException;
import com.wizardsofcode.deckmanagerserver.service.operations.ServerSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private UserActivationDAO activationDao;

    private ServerSettingsService serverSettings;

    private UserDAO userDAO;

    private EmailNotificationService notificationService;

    private UserDeckDAO userDeckDAO;

    @Autowired
    public UserActivationServiceImpl(UserActivationDAO activationDao, ServerSettingsService serverSettings,
                                     UserDAO userDAO, EmailNotificationService notificationService, UserDeckDAO userDeckDAO){
        this.activationDao = activationDao;
        this.serverSettings = serverSettings;
        this.userDAO = userDAO;
        this.notificationService = notificationService;
        this.userDeckDAO = userDeckDAO;
    }

    @Override
    public boolean activateUser(UUID uuid) throws NonValidActivationPinException {

        UserActivation userActivation = activationDao.findOne(uuid);
        userActivation.getUser().setActive(true);
        notificationService.sendActivatedMessage(userActivation.getUser());
        userDeckDAO.save(new UserDeck(true,userActivation.getUser()));
        activationDao.delete(userActivation);
        userDAO.save(userActivation.getUser());
        return true;
    }

    @Override
    public UserActivation createUserActivation(User u) {

        UserActivation activation = new UserActivation(u);
        activation = activationDao.save(activation);
        notificationService.sendActivationMessage(activation);
        activation.setUrl(serverSettings.retrieveSetting("SERVER_URL").getValue()
                + "activate/" +activation.getActivationId().toString());
        return activationDao.save(activation);
    }

}
