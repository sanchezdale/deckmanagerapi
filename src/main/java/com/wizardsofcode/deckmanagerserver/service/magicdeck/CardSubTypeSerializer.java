/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/10/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSubtype;


import java.io.IOException;

public class CardSubTypeSerializer extends JsonSerializer<CardSubtype> {
    @Override
    public void serialize(CardSubtype value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString( value.getSubtypeNAme());
    }
}
