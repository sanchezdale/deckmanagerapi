/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.service.userinventory;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.userinventory.CardInDeck;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import org.springframework.data.domain.Page;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserInventoryService {

    AddCardResponse addCardsToDeck(User user, AddInventoryTransportObject inventoryTransportObject) throws Exception;

    List<UserDeck> getAllUserDecks(User user);

    List<UserDeck> listAllUserDecks(User user);

    List<UserDeck> getAllPublicSets();

    List<UserDeck> getAllPublicSets(User user);

    UserDeck getOneDeck(long deck);

    AddCardResponse removeFromDeck(User u, AddInventoryTransportObject transportObject) throws Exception;

    UserDeck createUserDeck(UserDeck userDeck);

    UserDeck updateUserDeck(UserDeck userDeck) throws Exception;

    boolean removeDeck(User user, Long deckId) throws Exception;

    UserDeck addCardsToUserDeckBatchCsv(Optional<File> csvFile);

    UserDeck addCardsToUserDeckBatchExcel(Optional<File> excelFile);

    UserDeck getDefaultDeck(User user);

}
