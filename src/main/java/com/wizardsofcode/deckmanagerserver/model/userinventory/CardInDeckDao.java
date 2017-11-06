/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.userinventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInDeckDao extends JpaRepository<CardInDeck,UserDeckCardId>{


}
