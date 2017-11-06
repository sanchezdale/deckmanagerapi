package com.wizardsofcode.deckmanagerserver.service.cardmanagement;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardManagementService {
    //CardDAO Functions
    Card getCarById(int id);
    Page<Card> getCardsBySet(Pageable pageable, String setcode);
    Page<Card> getCardsBySet(Pageable pageable, int setid);
    Page<Card> getCardsByRarity(Pageable pageable, String rarityname);
    Page<Card> getCardsByManaCost(Pageable pageable, CardManaCost manaCost);
    Page<Card> getCardsByColorIdentity(Pageable pageable, CardColor color);

    //CardSetDAO Functions
    Page<CardSet> getAllSets(Pageable pageable);
    CardSet getSetById(int setid);
    CardSet getSetByCode(String setcode);
    Page<CardSet> getSetBySetNameLike(Pageable pageable, String setname);

    //CardColorDAO Functions
    CardColor getCardColorById(int id);
    Page<CardColor> getCardsByManaName(Pageable pageable, String mananame);
    Page<CardColor> getCardsByManaSymbol(Pageable pageable, String manasymbol);

    //CardSubTypeDAO Functions
    CardSubtype getCardSubTypeById(int id);
    Page<CardSubtype> getCardsBySubTypeName(Pageable pageable, String subtypename);
    CardSubtype getSubTypeByName(String subtypename);

    //CardSuperTypeDAO Functions
    CardSuperType getCardSuperTypeById(int id);
    CardSuperType getSuperTypeByName(String typename);

    //CardTypeDAO Functions
    CardType getCardTypeById(int id);
    CardType getCardTypeByName(String cardtypename);

    //CardArtistDAO Functions
    CardArtist getCardArtistById(int id);
    Page<CardArtist> getCardsByCardArtistName(Pageable pageable, String cardartistname);
    CardArtist getCardArtistByName(String cardartistname);

    //CardLayoutDAO Functions
    CardLayout getCardLayoutById(int id);
    CardLayout getCardLayoutByName(String cardlayoutname);
    Page<CardLayout> getCardsByLayoutName(Pageable pageable, String layoutname);

}
