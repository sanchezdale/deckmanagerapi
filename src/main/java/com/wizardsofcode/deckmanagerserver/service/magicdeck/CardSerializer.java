/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/9/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;


import java.io.IOException;
import java.lang.reflect.Field;

public class CardSerializer extends JsonSerializer<Card> {


    @Override
    public void serialize(Card value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        gen.writeStartObject();

        if(value.getCountOnDeck() > 0)
            gen.writeNumberField("count",value.getCountOnDeck());

        gen.writeNumberField(CardField.getFieldValue(CardField.CARD_ID), value.getCardId());
        gen.writeStringField(CardField.getFieldValue(CardField.CARD_NAME), value.getName());
        if (value.getMultiverseId() != null)
            gen.writeNumberField(CardField.getFieldValue(CardField.MULTIVERSE_ID), value.getMultiverseId());

        if ((serializers.getActiveView() != View.Summary.class)) {
            gen.writeStringField(CardField.getFieldValue(CardField.SET_NAME), value.getSet().getSetName());
            gen.writeStringField(CardField.getFieldValue(CardField.SET_CODE), value.getSet().getSetCode());
            if (value.getCardText() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.CARD_TEXT), value.getCardText());

            gen.writeStringField(CardField.getFieldValue(CardField.ARTIST_NAME), value.getArtist().getArtistName());
            if (value.getCmc() != null)
                gen.writeNumberField(CardField.getFieldValue(CardField.CMC), value.getCmc());
            if (value.getFlavor() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.FLAVOR), value.getFlavor());
            if (value.getUuid() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.CARD_UUID), value.getUuid());
            if (value.getImageName() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.IMAGE_NAME), value.getImageName());
            if (value.getLayout() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.LAYOUT), value.getLayout().getLayoutName());
            if (value.getMciNumber() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.MCI_NUMBER), value.getMciNumber());
            if (value.getPower() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.POWER), value.getPower());
            if (value.getToughness() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.TOUGHNESS), value.getToughness());
            if (value.getRarity() != null)
                gen.writeStringField(CardField.getFieldValue(CardField.RARITY), value.getRarity().getRarityName());
            if (value.getLifeModifier() != null)
                gen.writeNumberField(CardField.getFieldValue(CardField.LIFE_MODIFIER), value.getLifeModifier());
            if (value.getHandModifier() != null)
                gen.writeNumberField(CardField.getFieldValue(CardField.HAND_MODIFIER), value.getHandModifier());

            Hibernate.initialize(value.getCardManaCosts());
            if (Hibernate.isInitialized(value.getCardManaCosts())) {
                if (value.getCardManaCosts().size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CardManaCost manaCost : value.getCardManaCosts()) {
                        if (manaCost.getCardManaId().getManaId().getManaName().equals("colorless"))
                            sb.append("{" + manaCost.getCost() + "}");
                        else
                            for (int i = 0; i < manaCost.getCost(); i++)
                                sb.append("{" + manaCost.getCardManaId().getManaId().getManaSymbol() + "}");
                    }
                    gen.writeStringField(CardField.getFieldValue(CardField.MANA_COST), sb.toString());
                }
            }

            Hibernate.initialize(value.getCardSuperTypes());
            if (Hibernate.isInitialized(value.getCardSuperTypes())) {
                if (value.getCardSuperTypes().size() > 0) {
                    gen.writeArrayFieldStart(CardField.getFieldValue(CardField.SUPERTYPES));
                    for (CardSuperType superType : value.getCardSuperTypes()) {
                        gen.writeString(superType.getTypeName());
                        stringBuilder.append(superType.getTypeName() + " ");
                    }
                    gen.writeEndArray();
                }
            }
            Hibernate.initialize(value.getCardTypes());
            if (Hibernate.isInitialized(value.getCardTypes())) {
                if (value.getCardTypes().size() > 0) {
                    gen.writeArrayFieldStart(CardField.getFieldValue(CardField.TYPES));
                    for (CardType type : value.getCardTypes()) {
                        stringBuilder.append(type.getTypeName() + " ");
                        gen.writeString(type.getTypeName());
                    }
                    gen.writeEndArray();
                }
            }
            Hibernate.initialize(value.getCardSubtypes());
            if (Hibernate.isInitialized(value.getCardSubtypes())) {
                if (value.getCardSubtypes().size() > 0) {
                    gen.writeArrayFieldStart(CardField.getFieldValue(CardField.SUBTYPES));
                    stringBuilder.append(" -");
                    for (CardSubtype subtype : value.getCardSubtypes()) {
                        stringBuilder.append(" " + subtype.getSubtypeNAme());
                        gen.writeString(subtype.getSubtypeNAme());
                    }
                    gen.writeEndArray();
                }
            }

            Hibernate.initialize(value.getColorIdentity());
            if (Hibernate.isInitialized(value.getColorIdentity())) {
                if (value.getColorIdentity().size() > 0) {
                    gen.writeArrayFieldStart(CardField.getFieldValue(CardField.COLOR_IDENTITY));
                    for (CardColor color : value.getColorIdentity()) {
                        gen.writeString(color.getManaSymbol());
                    }
                    gen.writeEndArray();
                }
            }
            if (stringBuilder.toString().length() > 0)
                gen.writeStringField(CardField.getFieldValue(CardField.FULL_TYPE), stringBuilder.toString());


        }
        gen.writeEndObject();
    }

}
