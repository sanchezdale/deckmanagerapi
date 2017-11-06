package com.wizardsofcode.deckmanagerserver.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import com.wizardsofcode.deckmanagerserver.service.cardmanagement.CardManagementService;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/card")
public class CardManagementController {


    private CardManagementService cardManagementService;

    @Autowired
    public CardManagementController(CardManagementService cardManagementService){
        this.cardManagementService = cardManagementService;
    }

    @RequestMapping(value = "/get/{cardId}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardById(@PathVariable String cardId) throws Exception{
        Card card = cardManagementService.getCarById(Integer.valueOf(cardId));
        if (card != null)
            return new ResponseEntity<>(card, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/set/get/{setInfo}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardBySet(@PathVariable String setInfo, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items){

        Page page;

        try {
           int setID = Integer.valueOf(setInfo);
           page = cardManagementService.getCardsBySet(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)),
                   setID);
        } catch (Exception e) {
            page = cardManagementService.getCardsBySet(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)),
                    setInfo);
        }
        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/color/get/{manaColorName}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardsByColorName(@PathVariable String manaColorName, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception {
        Page page;

        if (manaColorName.length() == 1)
            page = cardManagementService.getCardsByManaSymbol(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), manaColorName);
        else
            page = cardManagementService.getCardsByManaName(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), manaColorName);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/rarity/get/{rarityName}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardByRarity(@PathVariable String rarityName, @RequestParam(defaultValue =  "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception {

        Page page;
        page = cardManagementService.getCardsByRarity(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), rarityName);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/color/get/{manaCost}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardsByManaCost(@PathVariable CardManaCost manaCost, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception{

        Page page;
        page = cardManagementService.getCardsByManaCost(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), manaCost);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/color/get/{colorIdentity}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardsByColorIdentity(@PathVariable CardColor cardColor, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception {

        Page page;
        page = cardManagementService.getCardsByColorIdentity(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), cardColor);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cardType/get/{cardTypeName}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardTypeByName(@PathVariable String cardTypeName) throws Exception {

        CardType cardType = cardManagementService.getCardTypeByName(cardTypeName);

        if (cardType != null)
            return new ResponseEntity<>(cardType, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/superType/get/{superTypeName}", method = RequestMethod.GET)
    public ResponseEntity<?>getSuperTypeByName(@PathVariable String superTypeName) throws Exception {

        CardSuperType cardSuperType = cardManagementService.getSuperTypeByName(superTypeName);

        if (cardSuperType != null)
            return new ResponseEntity<>(cardSuperType, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/artists/get/{artistName}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardsByCardArtistName(@PathVariable String artistName, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception {

        Page page;
        page = cardManagementService.getCardsByCardArtistName(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)), artistName);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/layout/get/{layoutName}", method = RequestMethod.GET)
    public ResponseEntity<?>getCardsByLayoutName(@PathVariable String layoutName, @RequestParam(defaultValue = "0", name = "page")
            String pageNumber, @RequestParam(defaultValue = "10") String items) throws Exception {

        Page page;
        page = cardManagementService.getCardsByLayoutName(new PageRequest(Integer.valueOf(pageNumber), Integer.valueOf(items)),layoutName);

        if (page != null)
            return new ResponseEntity<>(page, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
