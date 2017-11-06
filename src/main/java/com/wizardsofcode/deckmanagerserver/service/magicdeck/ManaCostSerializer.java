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
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardColor;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardManaCost;

import java.io.IOException;

public class ManaCostSerializer extends JsonSerializer<CardManaCost> {

    @Override
    public void serialize(CardManaCost value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        CardColor colorCode = value.getCardManaId().getManaId();
        int cost = value.getCost();

        StringBuilder sb = new StringBuilder();

        if(colorCode.getManaName().equals("colorless"))
            sb.append("{" + cost + "}");
        else
            for(int i = 0; i< cost; i++)
                sb.append(" {" + colorCode.getManaSymbol() + "}");
        gen.writeString(sb.toString());


    }
}
