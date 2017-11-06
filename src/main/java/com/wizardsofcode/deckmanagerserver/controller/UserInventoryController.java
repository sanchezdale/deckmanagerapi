/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import com.wizardsofcode.deckmanagerserver.model.operations.RestResponse;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserDAO;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UnauthorizedOnDeckException;
import com.wizardsofcode.deckmanagerserver.service.userinventory.AddInventoryTransportObject;
import com.wizardsofcode.deckmanagerserver.service.userinventory.UserInventoryService;
import javassist.NotFoundException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserInventoryController {


    private UserInventoryService userInventoryService;
    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserInventoryController.class);

    @Autowired
    public UserInventoryController(UserInventoryService inventoryService, UserDAO userDAO) {
        this.userInventoryService = inventoryService;
        this.userDAO = userDAO;
    }

    @JsonView(View.Summary.class)
    @Transactional
    @RequestMapping(value = "/deck/create", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createDeck(Principal principal, @RequestBody UserDeck deck) {

        User user = userDAO.findByUsername(principal.getName());
        deck.setOwner(user);
        if (user == null) {
            return new ResponseEntity<Object>(new RestResponse("UserNotFoundException",
                    "Non Valid Authentication or user not active", HttpStatus.CONFLICT)
                    , HttpStatus.CONFLICT);
        }
        Hibernate.initialize(user.getDecks());
        if (user.getDecks().contains(deck)) {
            return new ResponseEntity<Object>(new RestResponse("DataIntegrityViolation",
                    "The deck your are trying to create alreaddy exists for this user", HttpStatus.CONFLICT
                    , MDC.get("requestId"))
                    , HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<Object>(userInventoryService.createUserDeck(deck), HttpStatus.OK);

        }

    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/deck/get", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDecks(Principal principal,
                                         @RequestParam(value = "summary", defaultValue = "true") String summary) throws Exception {

        User user = userDAO.findByUsername(principal.getName());
        if (user == null) {
            throw new NotFoundException("The token supplied is not valid");
        }
        return new ResponseEntity<>(userInventoryService.listAllUserDecks(user), HttpStatus.OK);

    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/deck/get/user/{username}")
    public ResponseEntity<?> getUserPublicDecks(@PathVariable String username) {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<Object>(new RestResponse("UserNotFoundException",
                    "The username supplied was not found", HttpStatus.NOT_FOUND, MDC.get("requestId")),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(userInventoryService.getAllPublicSets(user), HttpStatus.OK);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/deck/getall", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPublicDecks() {
        return new ResponseEntity<Object>(userInventoryService.getAllPublicSets(), HttpStatus.OK);
    }

    @JsonView(View.deckView.class)
    @Transactional
    @RequestMapping(value = "/deck/get/{deckId}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneDeck(Principal principal, @PathVariable String deckId) throws Exception {
        logger.debug("Method: getOneDeck() Principal: " + principal + " deckId: " + deckId);

        User user = userDAO.findByUsername(principal.getName());

        UserDeck deck = userInventoryService.getOneDeck(Long.valueOf(deckId));

        if (user == null) {
            logger.debug("Method: getOneDeck() Exception: user is null");
            throw new UsernameNotFoundException("The token supplied is not valid");
        }
        if (deck == null) {
            logger.debug("Method: getOneDeck() Exception: Deck not found");
            return new ResponseEntity<Object>(new RestResponse("DeckNotFoundException",
                    "The deck id supplied does not exist", HttpStatus.NOT_FOUND, MDC.get("requestId")),
                    HttpStatus.NOT_FOUND);
        } else if ((deck.getOwner().getUserId() != user.getUserId()) && !deck.isPublic()) {
            logger.debug("Method: getOneDeck() Exception: User not allowed, the deck is private");
            throw new UnauthorizedOnDeckException("The deck you are trying to access is not public");
        }
        logger.debug("Method: getOneDeck() Success, Retrieved Entity: " + deck);
        Hibernate.initialize(deck.getCardsInDeck());
        return new ResponseEntity<Object>(deck, HttpStatus.OK);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/deck/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateDeck(Principal principal, @RequestBody UserDeck userDeck) throws Exception {
        User user = userDAO.findByUsername(principal.getName());
        userDeck.setOwner(user);

        if (user.getDecks().contains(userDeck))
            return new ResponseEntity<Object>(new RestResponse("DataIntegrityViolation",
                    "The deck your are trying to create alreaddy exists for this user", HttpStatus.CONFLICT
                    , MDC.get("requestId"))
                    , HttpStatus.CONFLICT);

        UserDeck deck = userInventoryService.updateUserDeck(userDeck);
        return new ResponseEntity<Object>(deck, HttpStatus.OK);
    }


    @RequestMapping(value = "/deck/delete/{deckId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeDeck(Principal principal, @PathVariable String deckId) throws Exception {

        User user = userDAO.findByUsername(principal.getName());
        if (userInventoryService.removeDeck(user, Long.valueOf(deckId))) {
            return new ResponseEntity<Object>(new RestResponse("null", "The deck was removed successfully",
                    HttpStatus.OK, MDC.get("requestId")), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new RestResponse("null", "The deck could not be removed",
                    HttpStatus.OK, MDC.get("requestId")), HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/inventory/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCardsToDeck(Principal principal, @RequestBody AddInventoryTransportObject cards) throws Exception {
        User user = userDAO.findByUsername(principal.getName());

        if (user == null)
            throw new UsernameNotFoundException("The token supplied is not valid");

        if (cards.getListOfCards().size() < 1)
            return new ResponseEntity<Object>(new RestResponse("EmptyListException", "The list supplied is empty",
                    HttpStatus.BAD_REQUEST, MDC.get("requestId")), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Object>(userInventoryService.addCardsToDeck(user, cards), HttpStatus.OK);
    }

    @RequestMapping(value = "/inventory/remove", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> removeCardsFromDeck(Principal principal, @RequestBody AddInventoryTransportObject transportObject) throws Exception{

        User user = userDAO.findByUsername(principal.getName());

        if(user == null)
            throw new UsernameNotFoundException("The token supplied is not valid");

        if (transportObject.getListOfCards().size() < 1)
            return new ResponseEntity<Object>(new RestResponse("EmptyListException", "The list supplied is empty",
                    HttpStatus.BAD_REQUEST, MDC.get("requestId")), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Object>(userInventoryService.removeFromDeck(user, transportObject), HttpStatus.OK);
    }

}
