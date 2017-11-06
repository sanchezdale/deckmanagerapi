/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/28/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.userinventory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import com.wizardsofcode.deckmanagerserver.model.userinventory.CardInDeck;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import org.hibernate.Hibernate;

import java.io.IOException;

public class DeckSerializer extends JsonSerializer<UserDeck> {


    @Override
    public void serialize(UserDeck value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("deckId",value.getDeckId());
        gen.writeStringField("deckName",value.getDeckName());
        gen.writeStringField("owner", value.getOwnerUsername());
        gen.writeBooleanField("isPublic",value.isPublic());
        gen.writeStringField("created",value.getCreated().toString());
        gen.writeNumberField("numOfCards", value.getNumOfCards());

        if(serializers.getActiveView().equals(View.Summary.class)){
            gen.writeEndObject();
        }else{
            gen.writeArrayFieldStart("cards");
            Hibernate.initialize(value.getCardsInDeck());
            for(CardInDeck card : value.getCardsInDeck()){
                card.getDeckId().getCardId().setCountOnDeck(card.getCount());
                gen.writeObject(card.getDeckId().getCardId());
            }
            gen.writeEndArray();
            gen.writeEndObject();
        }

    }
}
