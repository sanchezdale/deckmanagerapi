package com.wizardsofcode.deckmanagerserver.service.cardmanagement;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardManagementServiceImpl implements CardManagementService {

   @Autowired
    CardDAO cardDAO;
   @Autowired
    CardRarityDAO cardRarityDAO;
   @Autowired
    CardSetDAO cardSetDAO;
   @Autowired
   CardLayoutDAO cardLayoutDAO;
   @Autowired
   CardColorDAO cardColorDAO;
   @Autowired
   CardArtistDAO cardArtistDAO;
   @Autowired
   CardSubtypeDAO cardSubtypeDAO;
   @Autowired
   CardSuperTypeDAO cardSuperTypeDAO;
   @Autowired
   CardTypeDAO cardTypeDAO;

   /////////////////////Card Impl//////////////////
    @Override
    public Card getCarById(int id) {
        return cardDAO.findOne(id);
    }

    @Override
    public Page<Card> getCardsBySet(Pageable pageable, String setcode) {
        return cardDAO.findBySet_SetCode(pageable, setcode);
    }

    @Override
    public Page<Card> getCardsBySet(Pageable pageable, int setid) {
        CardSet cardSet = cardSetDAO.findOne(setid);
        return cardDAO.findBySet(pageable, cardSet);
    }

    @Override
    public Page<Card> getCardsByRarity(Pageable pageable, String rarityname) {
        CardRarity cardRarity = cardRarityDAO.findByRarityName(rarityname);
        return cardDAO.findByRarity(pageable, cardRarity);
    }

    @Override
    public Page<Card> getCardsByManaCost(Pageable pageable, CardManaCost manaCost) {
        return cardDAO.findByCardManaCosts(pageable, manaCost);
    }

    @Override
    public Page<Card> getCardsByColorIdentity(Pageable pageable, CardColor color) {
        return cardDAO.findByColorIdentity(pageable, color);
    }

    ////////////////Card Set Impl///////////////////
    @Override
    public Page<CardSet> getAllSets(Pageable pageable) {
        return cardSetDAO.findAll(pageable);
    }

    @Override
    public CardSet getSetById(int setid) {
        return cardSetDAO.findOne(setid);
    }

    @Override
    public CardSet getSetByCode(String setcode) {
        return cardSetDAO.findBySetCode(setcode);
    }

    @Override
    public Page<CardSet> getSetBySetNameLike(Pageable pageable, String setname) {
        return cardSetDAO.findBySetNameLike(pageable, setname);
    }

    ///////////////Card Color Impl////////////
    @Override
    public CardColor getCardColorById(int id) {
        return cardColorDAO.findOne(id);
    }

    @Override
    public Page<CardColor> getCardsByManaName(Pageable pageable, String mananame) {
        return cardColorDAO.findAllByManaName(pageable, mananame);
    }

    @Override
    public Page<CardColor> getCardsByManaSymbol(Pageable pageable, String manasymbol) {
        return cardColorDAO.findAllByManaSymbol(pageable, manasymbol);
    }

    /////////////Card Subtype Impl/////////////
    @Override
    public CardSubtype getCardSubTypeById(int id) {
        return cardSubtypeDAO.findOne(id);
    }

    @Override
    public Page<CardSubtype> getCardsBySubTypeName(Pageable pageable, String subtypename) {
        return cardSubtypeDAO.findAllBySubtypeName(pageable, subtypename);
    }

    @Override
    public CardSubtype getSubTypeByName(String subtypename) {
        return cardSubtypeDAO.findBySubtypeName(subtypename);
    }

    ////////////Card SuperType Impl///////////////
    @Override
    public CardSuperType getCardSuperTypeById(int id) {
        return cardSuperTypeDAO.findOne(id);
    }

    @Override
    public CardSuperType getSuperTypeByName(String typename) {
        return cardSuperTypeDAO.findByTypeName(typename);
    }

    //////////////Card Type Impl/////////////////
    @Override
    public CardType getCardTypeById(int id) {
        return cardTypeDAO.findOne(id);
    }

    @Override
    public CardType getCardTypeByName(String typename) {
        return cardTypeDAO.findByTypeName(typename);
    }

    ///////////Card Artist Impl/////////////////
    @Override
    public CardArtist getCardArtistById(int id) {
        return cardArtistDAO.findOne(id);
    }

    @Override
    public Page<CardArtist> getCardsByCardArtistName(Pageable pageable, String cardartistname) {
        return cardArtistDAO.findByArtistNameLike(pageable, cardartistname);
    }

    @Override
    public CardArtist getCardArtistByName(String cardartistname) {
        return cardArtistDAO.findByArtistNameEquals(cardartistname);
    }

    ////////////Card Layout Impl///////////////////
    @Override
    public CardLayout getCardLayoutById(int id) {
        return cardLayoutDAO.findOne(id);
    }

    @Override
    public CardLayout getCardLayoutByName(String cardlayourname) {
        return cardLayoutDAO.findByLayoutNameEquals(cardlayourname);
    }

    @Override
    public Page<CardLayout> getCardsByLayoutName(Pageable pageable, String layoutname) {
        return cardLayoutDAO.findByLayoutName(pageable, layoutname);
    }
}
