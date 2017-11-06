/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "card_layout")
public class CardLayout implements Serializable {


    private static final long serialVersionUID = -6698711946319373571L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_layout")
    @JsonIgnore
    private int cardLayout;

    @Column(name = "layout_name")
    private String layoutName;

    @OneToMany(mappedBy = "layout")
    @JsonManagedReference("layout_card")
    @JsonIgnore
    private List<Card> layoutsCards;

    public CardLayout() {
    }

    public CardLayout(String layoutName) {
        this.layoutName = layoutName;
    }

    public int getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(int cardLayout) {
        this.cardLayout = cardLayout;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public List<Card> getLayoutsCards() {
        return layoutsCards;
    }

    public void setLayoutsCards(List<Card> layoutsCards) {
        this.layoutsCards = layoutsCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardLayout that = (CardLayout) o;

        if (cardLayout != that.cardLayout) return false;
        return layoutName.equals(that.layoutName);
    }

    @Override
    public int hashCode() {
        int result = cardLayout;
        result = 31 * result + layoutName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardLayout{" +
                "cardLayout=" + cardLayout +
                ", layoutName='" + layoutName + '\'' +
                '}';
    }
}
