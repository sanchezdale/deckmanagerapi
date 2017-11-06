/* *                            * *
* * Created by: Daniel Sanchez  * *
* *                             * *
 */


package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.wizardsofcode.deckmanagerserver.config.DatabaseConfig;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.EmailNotificationService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserActivationService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserManagementService;
import com.wizardsofcode.deckmanagerserver.service.usermanagement.UserRolesService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@ComponentScan(value = {"com.wizardsofcode.deckmanagerserver"})
@SpringBootTest(classes = {DatabaseConfig.class})
public class UserManagementServiceTest {


    @MockBean
    private EmailNotificationService emailService;

    @Autowired
    private UserManagementService userManagementServiceImpl;

    @MockBean
    private UserRolesService roleService;

    @MockBean
    private UserActivationService activationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @BeforeClass
    @Sql("/notimport.sql")
    public static void runimort(){

    }

    @Test
    public void registerUserRoleUser(){

        // Test data

        String testPasssword = "testP4";
        User u = new User("usernameTest", testPasssword, "testName");

        UserEmail email = new UserEmail("emai@usernametest.com");

        UserRole role = new UserRole("ROLE_USER",null);
        role.setRoleId(2);

        UserActivation activationMock = new UserActivation(u);


        //Setting up Mocks
        given(roleService.retrieveUserRole()).willReturn(role);
        given(activationService.createUserActivation(u)).willReturn(activationMock);


        u.setEmail(email);

        userManagementServiceImpl.registerUser(u);

        //Verify Interactions and assertions
        verify(emailService, times(1)).sendActivationMessage(u.getUserActivation());
        Assert.assertEquals(u, userManagementServiceImpl.retrieveUserByUsername(u.getUsername()));
        Assert.assertTrue(passwordEncoder.matches(testPasssword, u.getPassword()));

    }
    @Test
    public void registerUserRoleAdmin() {
        // Test data
        String testPasssword = "testP4";
        User u = new User("adminTest", testPasssword, "testName");

        UserEmail email = new UserEmail("email@usernametest.com");

        UserRole role = new UserRole("ROLE_ADMIN",null);
        role.setRoleId(1);

        UserActivation activationMock = new UserActivation(u);


        //Setting up Mocks
        given(roleService.retrieveAdminRole()).willReturn(role);
        given(activationService.createUserActivation(u)).willReturn(activationMock);



        u.setEmail(email);
        userManagementServiceImpl.registerUser(u);

        //Verify Interactions and assertions
        verify(emailService, times(1)).sendActivationMessage(u.getUserActivation());
        Assert.assertEquals(u, userManagementServiceImpl.retrieveUserByUsername(u.getUsername()));
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void registerUserInvalidEmail(){
        // Test data
        String testPasssword = "testP4";
        User u = new User("user3Test", testPasssword, "testName");

        UserEmail email = new UserEmail("emailusernametest.com");

        u.setEmail(email);

        userManagementServiceImpl.registerUser(u);
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void registerUserInvalidPasswordRelaxedNegative(){
        String testPasssword = "tes4";
        User u = new User("user4Test", testPasssword, "testName");

        UserEmail email = new UserEmail("email@usernjametest.com");
        u.setEmail(email);
        userManagementServiceImpl.registerUser(u);
    }

    public void addPhoneNumberPositive(){

    }

    public void addPhoneNumberThatExistsNegative(){

    }

    public void updateEmailPositive(){

    }

    public void UpdateEmailNegative(){

    }

    public void updatePhoneNumber(){

    }

    public void updateRolePositive(){

    }


    public void createPasswordResetPositive(){

    }

    public void createPasswordResetInvalidUsernameNegative(){

    }

    public void createPasswordResetInvalidEmailNegative(){

    }

    public void checkPasswordCorrectPositive() {

    }

    public void checkPasswordIncorrect() {

    }

    public void resetPasswordPositive() {

    }

    public void resetPasswordExpiredTokenNegative(){

    }

    public void resetPasswordUsedToken(){

    }

    public void resetPasswordInvalidTokenNegative() {

    }

    public void updateUserNegative(){

    }

    public void disableUserAccount(){

    }

    public void updateAvatarPositive(){

    }

    public void updateAvatarIncorrectFormatNegative(){

    }

    public void updateAvatarSizeTooBigNegative(){

    }

    public void retrieveUsers(){

    }

    public void retrieveUserById(){

    }

    public void retrieveUserByIdNotFOundNegative(){

    }

    public void retrieveUserByUsername(){

    }

    public void retrieveUserByUsernameNotFoundNegative(){

    }

    public void retrieveUsersByName(){

    }


}