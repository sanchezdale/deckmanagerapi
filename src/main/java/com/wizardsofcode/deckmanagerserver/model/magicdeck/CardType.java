/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "card_type")
@Entity
public class CardType implements Serializable{


    private static final long serialVersionUID = -487779214672998461L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name",unique = true)
    @JsonView(View.name.class)
    private String typeName;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cardTypes")
    @JsonIgnoreProperties("cardTypes")
    private List<Card> cards;

    public CardType() {
    }

    public CardType(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

        CardType cardType = (CardType) o;

        if (typeId != cardType.typeId) return false;
        return typeName.equals(cardType.typeName);
    }

    @Override
    public int hashCode() {
        int result = typeId;
        result = 31 * result + typeName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}

