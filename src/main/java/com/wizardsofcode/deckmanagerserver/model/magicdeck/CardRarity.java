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

@Table(name = "card_rarity")
@Entity
@NamedEntityGraph(name = "rarityGraph", attributeNodes = {
        @NamedAttributeNode("rarityName")
})
public class CardRarity implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rarity_id")
    @JsonIgnore
    private int rarityId;

    @Column(name = "rarity_name", unique = true)
    private String rarityName;

    @OneToMany(mappedBy = "rarity")
    @JsonManagedReference("rarity_card")
    @JsonIgnore
    private List<Card> rarityCards;


    public CardRarity() {
    }

    public CardRarity(String rarityName) {
        this.rarityName = rarityName;
    }

    public int getRarityId() {
        return rarityId;
    }

    public void setRarityId(int rarityId) {
        this.rarityId = rarityId;
    }

    public String getRarityName() {
        return rarityName;
    }

    public void setRarityName(String rarityName) {
        this.rarityName = rarityName;
    }

    public List<Card> getRarityCards() {
        return rarityCards;
    }

    public void setRarityCards(List<Card> rarityCards) {
        this.rarityCards = rarityCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardRarity that = (CardRarity) o;

        if (rarityId != that.rarityId) return false;
        return rarityName.equals(that.rarityName);
    }

    @Override
    public int hashCode() {
        int result = rarityId;
        result = 31 * result + rarityName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardRarity{" +
                "rarityId=" + rarityId +
                ", rarityName='" + rarityName + '\'' +
                '}';
    }
}
