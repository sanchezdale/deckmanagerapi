/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/10/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSet;


import java.io.IOException;

public class SetSerializer extends JsonSerializer<CardSet> {
    @Override
    public void serialize(CardSet value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {

        gen.writeStartObject();
        gen.writeNumberField("id", value.getSetId());
        gen.writeStringField("name", value.getSetName());
        gen.writeStringField("code", value.getSetCode());
        gen.writeNumberField("count", value.getCount());
        gen.writeEndObject();
    }

}
