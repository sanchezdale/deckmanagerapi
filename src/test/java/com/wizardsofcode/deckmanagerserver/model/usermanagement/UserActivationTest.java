/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.wizardsofcode.deckmanagerserver.config.DatabaseConfig;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.NonValidActivationPinException;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserActivationService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserManagementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@ComponentScan(value = {"com.wizardsofcode.deckmanagerserver"})
@SpringBootTest(classes = {DatabaseConfig.class})
public class UserActivationTest {


    @Autowired
    private UserActivationService activationService;

    @Autowired
    private UserManagementService userManagementService;


    private String testUsername;

    @Test
    public void testActivateUser(){

        User u = new User("usernamestest1", "testP4", "testing 1");
        u.setEmail(new UserEmail("test@test.com"));

        u = userManagementService.saveUser(u);

        UserActivation testActivation = activationService.createUserActivation(u);

    }
}
