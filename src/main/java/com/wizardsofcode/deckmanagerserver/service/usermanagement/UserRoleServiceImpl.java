/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/13/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserAuthority;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserRole;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRolesService{

    @Autowired
    UserRoleDAO roleDAO;


    @Override
    public void createRole(UserRole role) {

    }

    @Override
    public void assignAuthorities(UserRole role, List<UserAuthority> authorities) {

    }

    @Override
    public UserRole retrieveUserRole() {

        return roleDAO.findByRoleName("ROLE_USER");
    }

    @Override
    public UserRole retrieveAdminRole() {
        return null;
    }
}
