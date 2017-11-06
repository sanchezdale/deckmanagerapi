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
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardType;

import java.io.IOException;

public class CardTypeDeserializer extends JsonDeserializer<CardType> {
    @Override
    public CardType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        CardType type = new CardType();
        JsonNode node = p.getCodec().readTree(p);

        type.setTypeId(node.get("typeId").asInt());
        type.setTypeName(node.get("type").asText());

        return type;

    }
}
