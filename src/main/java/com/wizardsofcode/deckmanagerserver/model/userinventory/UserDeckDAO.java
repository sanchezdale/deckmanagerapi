/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.userinventory;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDeckDAO extends JpaRepository<UserDeck,Long>{

    List<UserDeck> findAllByOwner(User user);

    List<UserDeck> findAllByIsPublicIsTrue();

    List<UserDeck> findAllByIsPublicIsTrueAndOwner(User user);

    UserDeck findByOwnerAndIsDefaultTrue(User user);
}
