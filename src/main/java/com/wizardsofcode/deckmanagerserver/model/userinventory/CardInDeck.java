/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/23/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.userinventory;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "deckId.cardId", joinColumns = @JoinColumn(name = "card_id")),
        @AssociationOverride(name = "deckId.userDeck", joinColumns = @JoinColumn(name = "user_deck_id"))})
@Table(name = "deck_has_card")
public class CardInDeck implements Serializable{


    private static final long serialVersionUID = -2259886981777536766L;

    @Id
    @JsonUnwrapped
    private UserDeckCardId deckId;

    @Column(name = "card_count")
    private int count;

    public CardInDeck(UserDeckCardId cardId, int count) {
        this.deckId = cardId;
        this.count = count;
    }

    public CardInDeck() {
    }

    public UserDeckCardId getDeckId() {
        return deckId;
    }

    public void setDeckId(UserDeckCardId deckId) {
        this.deckId = deckId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardInDeck that = (CardInDeck) o;

        if (count != that.count) return false;
        return deckId != null ? deckId.equals(that.deckId) : that.deckId == null;
    }

    @Override
    public int hashCode() {
        int result = deckId != null ? deckId.hashCode() : 0;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "CardInDeck{" +
                "deckId=" + deckId +
                ", count=" + count +
                '}';
    }
}
