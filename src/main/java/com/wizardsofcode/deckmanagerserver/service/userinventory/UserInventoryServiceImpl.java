/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.userinventory;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardDAO;
import com.wizardsofcode.deckmanagerserver.model.userinventory.*;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserDAO;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.UnauthorizedOnDeckException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserInventoryServiceImpl implements UserInventoryService {

    CardDAO cardRepository;

    UserDeckDAO deckRepository;

    UserDAO userRepository;

    CardInDeckDao cardInDeckDao;

    private static final Logger logger = LoggerFactory.getLogger(UserInventoryServiceImpl.class);


    @Autowired
    public UserInventoryServiceImpl(CardDAO cardRepository, UserDeckDAO deckRepository, UserDAO userRepository,
                                    CardInDeckDao cardInDeckDao){
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
        this.cardInDeckDao = cardInDeckDao;
    }

    @Override
    public AddCardResponse addCardsToDeck(User user, AddInventoryTransportObject inventoryTransportObject)throws Exception {
        logger.debug("Method: addCards() user:" + user.getUsername() + " payload: " + inventoryTransportObject);
        UserDeck deck = deckRepository.findOne(inventoryTransportObject.getDeckId());
        List<Integer> notFound = new ArrayList<>();
        Map<Integer,Integer> idsAndQuantities = new HashMap<>();

        Hibernate.initialize(user.getDecks());
        if(!user.getDecks().contains(deck)) {
            logger.debug("Method: addCards() UserNotAuthorized for deck: " + inventoryTransportObject.getDeckId() +
                    " user: " + user.getUsername());
            throw new UnauthorizedOnDeckException("This user is not authorized to modifiy this resource");
        }
        //Map ids to and add up repeated to create a quantity value

        for(int id : inventoryTransportObject.getListOfCards()){
            if(idsAndQuantities.containsKey(id)){
                idsAndQuantities.put(id,idsAndQuantities.get(id) + 1);
            }else{
                idsAndQuantities.put(id,1);
            }
        }
        // Check the cards on the deck for a match with the ones we are adding.
        Card card =null;
        Hibernate.initialize(deck.getCardsInDeck());
        if(deck.getCardsInDeck().size() > 0) {
            for (int id = 0; id < deck.getCardsInDeck().size(); id++) {
                int cardId = deck.getCardsInDeck().get(id).getDeckId().getCardId().getCardId();
                // Add more quantity to the ones they already have and delete from the list
                if (idsAndQuantities.containsKey(cardId)) {

                    deck.getCardsInDeck().get(id).
                            setCount(deck.getCardsInDeck().
                                    get(id).getCount() + idsAndQuantities.get(cardId));
                    idsAndQuantities.remove(cardId);
                }
            }
            cardInDeckDao.save(deck.getCardsInDeck());
        }
        // For the rest of the cards not on inventory - create neww entries
        for (int id : idsAndQuantities.keySet()) {
            card = cardRepository.findOne(id);

            if (card != null) {
                CardInDeck carddeck = new CardInDeck(new UserDeckCardId(card, deck), idsAndQuantities.get(id));
                cardInDeckDao.save(carddeck);
            }
            else
                notFound.add(id);
        }

        if(notFound.size() > 0){
            logger.debug("Method addCard() PARTIAL_SUCCESS");
            return new AddCardResponse("PARTIAL_SUCCESS", notFound,"Some cards could not be added as they were not found.");
        }else{
            logger.debug("Method addCard() SUCCESS");
            return new AddCardResponse("SUCCESS",null,"All the cards were successfully added");
        }
    }

    @Override
    public List<UserDeck> getAllUserDecks(User user) {
        logger.debug("Method getAllUserDecks() - user: " + user.getUsername());
        return deckRepository.findAllByOwner(user);
    }

    @Override
    public List<UserDeck> listAllUserDecks(User user) {
        return deckRepository.findAllByOwner(user);
    }

    @Override
    public List<UserDeck> getAllPublicSets() {
        return deckRepository.findAllByIsPublicIsTrue();
    }

    @Override
    public List<UserDeck> getAllPublicSets(User user) {
        return deckRepository.findAllByIsPublicIsTrueAndOwner(user);
    }

    @Override
    public UserDeck getOneDeck(long deckId) {
        return deckRepository.findOne(deckId);
    }

    @Override
    public AddCardResponse removeFromDeck(User u, AddInventoryTransportObject transportObject) throws Exception {

        UserDeck deck = deckRepository.findOne(transportObject.getDeckId());
        List<Integer> notRemoved = new ArrayList<>();
        List<CardInDeck> toDelete = new ArrayList<>();

        Map<Integer,Integer> idsAndQuatities = new HashMap<>();

        //If there is nothing on the deck, there is nothing to remove
        if(deck.getCardsInDeck().size() < 1)
            return new AddCardResponse("FAIL", transportObject.getListOfCards(),
                    "You are trying to remove from an empty deck");

        if (!deck.getOwner().getUsername().equals(u.getUsername()))
            throw new UnauthorizedOnDeckException("You cannot remove cards from a deck you are not owner");

        // Add quantities to delete
        for(int cardId : transportObject.getListOfCards()){
            if(idsAndQuatities.containsKey(cardId)){
                idsAndQuatities.put(cardId,idsAndQuatities.get(cardId) + 1);
            }else {
                idsAndQuatities.put(cardId, 1);
            }
        }
        //Check if the card to be removed exists on the deck. if not. just remove it
        notRemoved = idsAndQuatities.keySet().stream()
                .filter(key -> deck.getCardsInDeck().stream()
                        .map(card -> card.getDeckId().getCardId().getCardId()).allMatch(id -> !id.equals(key)))
                .collect(Collectors.toList());

        // a bit confusing - but one card id is the reference on the Embedabble and the last is the actual card id
        // lambda function to substract the amount - think about making it more readable
        deck.getCardsInDeck().stream().forEach(c -> {
            if (idsAndQuatities.containsKey(c.getDeckId().getCardId().getCardId()))
                c.setCount(c.getCount() - idsAndQuatities.get(c.getDeckId().getCardId().getCardId()));
                if(c.getCount() < 1) {
                    toDelete.add(c);
                }
        });

        // Update and delete deck entries
        cardInDeckDao.delete(toDelete);

        if (notRemoved.size() > 0) {
            logger.debug("Method removeCard() PARTIAL_SUCCESS");
            return new AddCardResponse("PARTIAL_SUCCESS", notRemoved, "Some cards could not be removed as they were not found.");
        }
        logger.debug("Method removeCard() SUCCESS");
        return new AddCardResponse("SUCCESS", notRemoved, "All the cards were successfully removed");
    }


    @Override
    public UserDeck createUserDeck(UserDeck deck) {
        return deckRepository.save(deck);
    }

    @Override
    public UserDeck updateUserDeck(UserDeck userDeck) throws Exception {
        logger.debug("Method updateUserDeck() updating: " + userDeck.getDeckId() + " payload: " + userDeck.getDeckName());
        UserDeck deck = deckRepository.getOne(userDeck.getDeckId());

        if(deck.isDefault())
            throw new UnsupportedOperationException("The default deck cannot be updated");

        deck.setDeckName(userDeck.getDeckName());
        deck.setIsPublic(userDeck.isPublic());

        if(!userDeck.getOwner().getUsername().equalsIgnoreCase(deck.getOwner().getUsername())){
            logger.debug("MEthod updateUserDeck() - user: "+ userDeck.getOwner().getUsername() +
                    "is not the owner of deck: " + userDeck.getDeckId());
            throw new UnauthorizedOnDeckException("This deck does not belong to the updating user");
        }

        logger.debug("Method updateUserDeck() SUCCESS");
        return deckRepository.save(userDeck);
    }

    @Override
    public boolean removeDeck(User user, Long deckID) throws Exception{

        UserDeck deck = deckRepository.findOne(deckID);
        if(deck.getOwner().getUserId() != user.getUserId())
            throw new UnauthorizedOnDeckException("The deck supplied does not belong to the user");
        if(deck.isDefault()){
            throw new UnauthorizedOnDeckException("The default deck cannot be deleted");
        }
        if(deck.isDefault()){
            throw new UnsupportedOperationException("The default deck cannot be removed");
        }
        deckRepository.delete(deckID);
        return true;
    }

    @Override
    public UserDeck addCardsToUserDeckBatchCsv(Optional<File> csvFile) {
        return null;
    }

    @Override
    public UserDeck addCardsToUserDeckBatchExcel(Optional<File> excelFile) {
        return null;
    }

    @Override
    public UserDeck getDefaultDeck(User user) {
       return deckRepository.findByOwnerAndIsDefaultTrue(user);
    }

}
