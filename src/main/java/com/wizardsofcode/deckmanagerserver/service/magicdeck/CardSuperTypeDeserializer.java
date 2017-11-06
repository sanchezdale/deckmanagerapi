/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/10/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSuperType;

import java.io.IOException;

public class CardSuperTypeDeserializer extends JsonDeserializer<CardSuperType> {
    @Override
    public CardSuperType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        CardSuperType type = new CardSuperType();
        JsonNode node = p.getCodec().readTree(p);

        type.setTypeId(node.get("supertypeId").asInt());
        type.setTypeName(node.get("supertype").asText());

        return type;
    }
}
