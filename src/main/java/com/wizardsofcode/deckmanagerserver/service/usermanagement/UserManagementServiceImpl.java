package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeckDAO;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserEmailDAO emailDao;

    @Autowired
    private UserActivationService userActivationService;

    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(User newUser) {

        UserRole role = userRolesService.retrieveUserRole();
        List<UserRole> user_roles = new ArrayList<>();
        user_roles.add(role);
        newUser.setUserRoles(user_roles);
        newUser = userDao.save(newUser);
        newUser.setUserActivation(userActivationService.createUserActivation(newUser));
        return newUser;
    }

    @Override
    public User updateUser(User user) {

        User persisted = userDao.findOne(user.getUserId());

        if(user.getFirstName() == null)
            user.setFirstName(user.getFirstName());

        if(user.getLastName() == null)
            user.setLastName(persisted.getLastName());

        if(user.getEmail() == null)
            user.setEmail(persisted.getEmail());
        else{
            persisted.getEmail().setEmail(user.getEmail().getEmail());
            user.setEmail(persisted.getEmail());
        }
        if(user.getPhoneNumber() == null){
            user.setPhoneNumber(persisted.getPhoneNumber());
        }

        user.setAvatar(persisted.getAvatar());
        user.setPassword(persisted.getPassword());
        user.setActive(persisted.isActive());
        user.setUserRoles(persisted.getUserRoles());
        userDao.save(user);
        return user;
    }

    @Override
    public User disableUser(User user) {
        user.setActive(false);
        userDao.save(user);
        return user;
    }

    @Override
    public User updateUserRole(User user) {
        return null;
    }

    @Override
    public User updateUserAvatar(User user) {
        return null;
    }

    @Override
    public User updatePasswordFromReset(UserPasswordReset passwordReset) {
        User user = userDao.findOne(passwordReset.getUser().getUserId());

        user.setPassword(passwordReset.getPassword());
        user.setPasswordUpdated(true);
        userDao.save(user);
        return user;
    }

    @Override
    public User updatePasswordFromUserRequest(String oldPassword, String newPassword, String principalUsername) throws UnauthorizedUserException{

        User persisted = userDao.findByUsername(principalUsername);

        if(passwordEncoder.matches(oldPassword,persisted.getPassword())){
            persisted.setPassword(newPassword);
            persisted.setPasswordUpdated(true);
        }else{
            throw new UnauthorizedUserException("Invalid credentials");
        }

        updateUser(persisted);
        return persisted;
    }

    @Override
    public User resetPassword(User user, UserPasswordReset resetToken) {
        return null;
    }

    @Override
    public User retrieveUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User saveUser(User user){
        return userDao.save(user);
    }

    @Override
    public User retrieveUserById(long id){
        return userDao.findOne(id);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return userDao.countByUsernameIs(username) > 0;
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return emailDao.countByEmailIs(email) > 0;
    }

}
