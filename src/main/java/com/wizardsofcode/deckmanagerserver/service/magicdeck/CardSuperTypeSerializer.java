/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/10/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSuperType;
import org.codehaus.jackson.JsonProcessingException;


import java.io.IOException;

public class CardSuperTypeSerializer extends JsonSerializer<CardSuperType> {
    @Override
    public void serialize(CardSuperType value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("supertype", value.getTypeName());
        jgen.writeEndObject();
    }
}
