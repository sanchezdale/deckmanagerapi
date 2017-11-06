/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/27/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDAO userDAO;

    private UserEmailDAO emailDao;

    @Autowired
    public CustomUserDetailsService(UserDAO userdao, UserEmailDAO emailDAO){
        this.userDAO = userdao;
        this.emailDao = emailDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        UserEmail email;

        if((user = userDAO.findByUsername(username)) != null){
            return new CustomUserDetails(user);

        }else if((email = emailDao.findByEmail(username)) != null){
            return new CustomUserDetails(userDAO.findByEmail(email));
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
