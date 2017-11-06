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
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSubtype;

import java.io.IOException;

public class CardSubTypeDeserializer extends JsonDeserializer<CardSubtype>{
    @Override
    public CardSubtype deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        CardSubtype type = new CardSubtype();
        JsonNode node = p.getCodec().readTree(p);

        type.setSubtypeId(node.get("subtypeId").asInt());
        type.setSubtypeNAme(node.get("subtype").asText());

        return type;
    }
}
