/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import java.io.Serializable;

public class CardQuery implements Serializable{

    private String query;

    public CardQuery(String query) {
        this.query = query;
    }

    public CardQuery() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardQuery cardQuery = (CardQuery) o;

        return query != null ? query.equals(cardQuery.query) : cardQuery.query == null;
    }

    @Override
    public int hashCode() {
        return query != null ? query.hashCode() : 0;
    }

    @Override
    public String
    toString() {
        return "CardQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
