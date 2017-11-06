package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CardManaId implements Serializable {


    private static final long serialVersionUID = -5635079722486349782L;


    @ManyToOne(targetEntity = Card.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    @JsonBackReference("mana_cost_card")
    private Card cardId;

    @ManyToOne(targetEntity = CardColor.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "mana_color_id")
    @JsonBackReference("mana_cost")
    private CardColor manaId;

    public CardManaId() {
    }

    public CardManaId(Card cardId, CardColor manaId) {
        this.cardId = cardId;
        this.manaId = manaId;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }

    public CardColor getManaId() {
        return manaId;
    }

    public void setManaId(CardColor manaId) {
        this.manaId = manaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardManaId that = (CardManaId) o;

        if (!cardId.equals(that.cardId)) return false;
        return manaId.equals(that.manaId);
    }

    @Override
    public int hashCode() {
        int result = cardId.hashCode();
        result = 31 * result + manaId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardManaId{" +
                "cardId=" + cardId +
                ", manaId=" + manaId +
                '}';
    }
}
