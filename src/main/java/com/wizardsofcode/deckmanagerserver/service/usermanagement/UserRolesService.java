/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/13/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserAuthority;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserRole;

import java.util.List;

public interface UserRolesService {


    void createRole(UserRole role);

    void assignAuthorities(UserRole role, List<UserAuthority> authorities);

    UserRole retrieveUserRole();

    UserRole retrieveAdminRole();

    // Could create convenience metods to get mosst common roles
}
