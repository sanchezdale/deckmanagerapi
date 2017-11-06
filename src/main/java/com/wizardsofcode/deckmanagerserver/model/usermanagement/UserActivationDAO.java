/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserActivationDAO extends JpaRepository<UserActivation, UUID> {

    UserActivation findByUser(User user);


}
