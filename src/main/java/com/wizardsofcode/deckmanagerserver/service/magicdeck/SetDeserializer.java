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
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSet;

import java.io.IOException;

public class SetDeserializer extends JsonDeserializer<CardSet> {

    @Override
    public CardSet deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        CardSet set = new CardSet();
        JsonNode node = p.getCodec().readTree(p);

        int id,count;
        String name,code;
        code = node.get("code").asText();
        name = node.get("name").asText();
        id = node.get("id").asInt();
        count = node.get("count").asInt();

        set.setSetId(id);
        set.setSetName(name);
        set.setCount(count);
        set.setSetCode(code);

        return set;

    }
}
