package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Table(name = "card_mana_colors")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardColor implements Serializable {


    private static final long serialVersionUID = 7118427391566913481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mana_color_id")
    private int colorId;

    @Column(name = "mana_name")
    @JsonView(View.name.class)
    private String manaName;

    @Column(name = "mana_symbol")
    private String manaSymbol;

    @OneToMany(mappedBy = "cardManaId.manaId")
    private Set<CardManaCost> cardManaCosts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "card_color_identity", joinColumns = {@JoinColumn(name = "mana_color_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "card_id", nullable = false, updatable = false)})
    @JsonManagedReference("card_color")
    @JsonIgnore
    private List<Card> cards;

    public CardColor() {
    }

    public CardColor(String manaName, String manaSymbol) {
        this.manaName = manaName;
        this.manaSymbol = manaSymbol;
    }



    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getManaName() {
        return manaName;
    }

    public void setManaName(String manaName) {
        this.manaName = manaName;
    }

    public String getManaSymbol() {
        return manaSymbol;
    }

    public void setManaSymbol(String manaSymbol) {
        this.manaSymbol = manaSymbol;
    }

    public Set<CardManaCost> getCardManaCosts() {
        return cardManaCosts;
    }

    public void setCardManaCosts(Set<CardManaCost> cardManaCosts) {
        this.cardManaCosts = cardManaCosts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardColor cardColor = (CardColor) o;

        if (colorId != cardColor.colorId) return false;
        if (!manaName.equals(cardColor.manaName)) return false;
        return manaSymbol.equals(cardColor.manaSymbol);
    }

    @Override
    public int hashCode() {
        int result = colorId;
        result = 31 * result + manaName.hashCode();
        result = 31 * result + manaSymbol.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardColor{" +
                "colorId=" + colorId +
                ", manaName='" + manaName + '\'' +
                ", manaSymbol='" + manaSymbol + '\'' +
                '}';
    }
}
