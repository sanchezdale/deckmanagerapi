/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.model.magicdeck;

public enum CardField {

    CARD_ID,CARD_NAME, CARD_TEXT, FLAVOR, SET_NAME, SET_CODE,
    ARTIST_NAME, CARD_UUID,IMAGE_NAME, LAYOUT, CMC, MULTIVERSE_ID,
    MCI_NUMBER, POWER, TOUGHNESS, RARITY, LIFE_MODIFIER,HAND_MODIFIER,
    TYPES, SUBTYPES, SUPERTYPES, MANA_COST, FULL_TYPE, COLOR_IDENTITY;

    public static String getFieldValue(CardField fields){
        switch (fields){
            case CARD_NAME:
                return "cardName";
            case CARD_TEXT:
                return "cardText";
            case CARD_UUID:
                return "uuid";
            case CARD_ID:
                return "cardId";
            case SET_CODE:
                return "setCode";
            case SET_NAME:
                return "setName";
            case MULTIVERSE_ID:
                return "multiverseId";
            case CMC:
                return "cmc";
            case POWER:
                return "power";
            case TOUGHNESS:
                return "toughness";
            case LAYOUT:
                return "layout";
            case ARTIST_NAME:
                return "artistName";
            case FLAVOR:
                return "flavor";
            case RARITY:
                return "rarity";
            case IMAGE_NAME:
                return "imageName";
            case HAND_MODIFIER:
                return "handModifier";
            case LIFE_MODIFIER:
                return "lifeModifier";
            case MCI_NUMBER:
                return "mciNumber";
            case TYPES:
                return "types";
            case SUBTYPES:
                return "subtypes";
            case SUPERTYPES:
                return "supertypes";
            case FULL_TYPE:
                return "type";
            case MANA_COST:
                return "manaCost";
            case COLOR_IDENTITY:
                return "colorIdentity";
            default:
                return null;
        }
    }
}
