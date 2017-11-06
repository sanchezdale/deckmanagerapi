/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "card_subtype")
@Cacheable
public class CardSubtype implements Serializable{


    private static final long serialVersionUID = -1264985914118736535L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtype_id")
    private int subtypeId;

    @Column(name = "subtype_name", unique = true)
    @JsonView(View.name.class)
    private String subtypeName;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cardSubtypes")
    @JsonIgnoreProperties("cardSubtypes")
    private Set<Card> cards;


    public CardSubtype() {
    }

    public CardSubtype(String subtypeNAme) {
        this.subtypeName = subtypeNAme;
    }

    public int getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(int subtypeId) {
        this.subtypeId = subtypeId;
    }

    public String getSubtypeNAme() {
        return subtypeName;
    }

    public void setSubtypeNAme(String subtypeNAme) {
        this.subtypeName = subtypeNAme;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardSubtype that = (CardSubtype) o;

        if (subtypeId != that.subtypeId) return false;
        return subtypeName.equals(that.subtypeName);
    }

    @Override
    public int hashCode() {
        int result = subtypeId;
        result = 31 * result + subtypeName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardSubtype{" +
                "subtypeId=" + subtypeId +
                ", subtypeNAme='" + subtypeName + '\'' +
                '}';
    }
}
