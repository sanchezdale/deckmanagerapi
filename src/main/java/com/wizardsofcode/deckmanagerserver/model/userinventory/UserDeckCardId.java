/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/23/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.userinventory;

import com.fasterxml.jackson.annotation.*;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserDeckCardId implements Serializable{

    @ManyToOne
    @JoinColumn( name = "card_id", updatable = false, insertable = false, nullable = false)
    @JsonProperty(value = "card")
    @JsonUnwrapped
    private Card cardId;

    @ManyToOne
    @JoinColumn(name = "user_deck_id",updatable = false,insertable = false,nullable = false)
    @JsonIgnore
    private UserDeck userDeck;

    public UserDeckCardId() {
    }

    public UserDeckCardId(Card cardId, UserDeck userDeck) {
        this.cardId = cardId;
        this.userDeck = userDeck;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }

    public void setUserDeck(UserDeck userDeck) {
        this.userDeck = userDeck;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDeckCardId that = (UserDeckCardId) o;
        if (!cardId.equals(that.cardId)) return false;
        return userDeck.equals(that.userDeck);
    }

    @Override
    public int hashCode() {
        int result = cardId.hashCode();
        result = 31 * result + userDeck.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDeckCardId{" +
                "cardId=" + cardId +
                ", userDeck=" + userDeck +
                '}';
    }
}
