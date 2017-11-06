/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.model.usermanagement;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {

    User findByUsername(String username);

    int countByUsernameIs(String username);

    User findByEmail(UserEmail email);



}
