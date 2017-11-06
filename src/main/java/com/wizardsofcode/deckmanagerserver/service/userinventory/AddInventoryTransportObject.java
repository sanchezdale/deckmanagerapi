/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.userinventory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddInventoryTransportObject {

    @JsonProperty(value = "deckId")
    private Long deckId;

    @JsonProperty(value = "cards")
    private List<Integer> listOfCards;

    public AddInventoryTransportObject(Long deckId) {
        this.deckId = deckId;
    }

    public AddInventoryTransportObject(Long deckId, List<Integer> listOfCards) {
        this.deckId = deckId;
        this.listOfCards = listOfCards;
    }

    public AddInventoryTransportObject() {
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public List<Integer> getListOfCards() {
        return listOfCards;
    }

    public void setListOfCards(List<Integer> listOfCards) {
        this.listOfCards = listOfCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddInventoryTransportObject that = (AddInventoryTransportObject) o;

        if (deckId != that.deckId) return false;
        return listOfCards != null ? listOfCards.equals(that.listOfCards) : that.listOfCards == null;
    }

    @Override
    public int hashCode() {
        long result = deckId;
        result = 31 * result + (listOfCards != null ? listOfCards.hashCode() : 0);
        return (int)result;
    }

    @Override
    public String toString() {
        return "AddInventoryTransportObject{" +
                "deckId=" + deckId +
                ", listOfCards=" + listOfCards +
                '}';
    }
}
