package com.wizardsofcode.deckmanagerserver.model.magicdeck;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@AssociationOverrides({
@AssociationOverride(name = "cardManaId.cardId", joinColumns = @JoinColumn(name = "card_id")),
@AssociationOverride(name = "cardManaId.manaId", joinColumns = @JoinColumn(name = "mana_color_id"))})
@Table(name = "card_mana_cost")
public class CardManaCost implements Serializable {


    private static final long serialVersionUID = -3801496527802966376L;

    @Id
    private CardManaId cardManaId;

    @Column(name = "cost")
    private int cost;


    public CardManaCost() {
        cardManaId = new CardManaId();
    }

    public CardManaCost(int cost) {
        cardManaId = new CardManaId();
        this.cost = cost;
    }

    public CardManaId getCardManaId() {
        return cardManaId;
    }

    public void setCardManaId(CardManaId cardManaId) {
        this.cardManaId = cardManaId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
