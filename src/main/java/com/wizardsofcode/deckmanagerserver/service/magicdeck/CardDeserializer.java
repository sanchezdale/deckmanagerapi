/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/9/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CardDeserializer extends JsonDeserializer<Card> {

    @Autowired
    CardSetDAO cardSetRepository;

    @Autowired
    CardArtistDAO artistRepository;

    @Autowired
    CardRarityDAO rarityRepository;

    @Autowired
    CardLayoutDAO layoutRepository;

    @Override
    public Card deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        int cardId;
        String setName, setCode, cardName, cardText, artistName;
        int cmc;
        String flavor, uuid, imagename, layout, power,toughness;
        String mciNumber;
        int multiverseId;
        String rarity;
        int lifeModifier, handModifier;
        Card card = new Card();

        cardId = node.get(CardField.getFieldValue(CardField.CARD_ID)).asInt();
        setName = node.get(CardField.getFieldValue(CardField.SET_NAME)).toString();
        setCode = node.get(CardField.getFieldValue(CardField.SET_CODE)).toString();
        cardName = node.get(CardField.getFieldValue(CardField.CARD_NAME)).toString();
        cardText = node.get(CardField.getFieldValue(CardField.CARD_TEXT)).toString();
        artistName = node.get(CardField.getFieldValue(CardField.ARTIST_NAME)).toString();
        cmc = node.get(CardField.getFieldValue(CardField.CMC)).asInt();
        flavor = node.get(CardField.getFieldValue(CardField.FLAVOR)).toString();
        uuid = node.get(CardField.getFieldValue(CardField.CARD_UUID)).toString();
        imagename = node.get(CardField.getFieldValue(CardField.IMAGE_NAME)).toString();
        layout = node.get(CardField.getFieldValue(CardField.LAYOUT)).toString();
        mciNumber = node.get(CardField.getFieldValue(CardField.MCI_NUMBER)).toString();
        multiverseId = node.get(CardField.getFieldValue(CardField.MULTIVERSE_ID)).asInt();
        power = node.get(CardField.getFieldValue(CardField.POWER)).toString();
        toughness = node.get(CardField.getFieldValue(CardField.TOUGHNESS)).toString();
        rarity = node.get(CardField.getFieldValue(CardField.RARITY)).toString();
        lifeModifier = node.get(CardField.getFieldValue(CardField.LIFE_MODIFIER)).asInt();
        handModifier = node.get(CardField.getFieldValue(CardField.HAND_MODIFIER)).asInt();

        Set<CardType> types = new HashSet<>();
        Set<CardSubtype> subtypes = new HashSet<>();
        Set<CardSuperType> superTypes = new HashSet<>();

        card.setCardId(cardId);
        card.setSet(cardSetRepository.findBySetCode(setCode));
        card.setName(cardName);
        card.setCardText(cardText);
        card.setArtist(artistRepository.findByArtistNameEquals(artistName));
        card.setCmc(cmc);
        card.setFlavor(flavor);
        card.setUuid(uuid);
        card.setImageName(imagename);
        card.setLayout(layoutRepository.findByLayoutNameEquals(layout));
        card.setMciNumber(mciNumber);
        card.setMultiverseId(multiverseId);
        card.setPower(power);
        card.setToughness(toughness);
        card.setLifeModifier(lifeModifier);
        card.setHandModifier(handModifier);

      return card;

    }
}
