/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "card_artist")
@Entity
@NamedEntityGraph(name = "artistGraph", attributeNodes = {
        @NamedAttributeNode("artistId"),@NamedAttributeNode("artistName")
})
@Cacheable
public class CardArtist implements Serializable {

    private static final long serialVersionUID = 7599497343298007694L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private int artistId;

    @Column(name = "artist_name", unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8")
    @JsonView(View.name.class)
    private String artistName;


    @OneToMany(mappedBy = "artist")
    @JsonManagedReference("artist_card")
    private List<Card> artistCards;

    public CardArtist() {
    }

    public CardArtist(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Card> getArtistCards() {
        return artistCards;
    }

    public void setArtistCards(List<Card> artistCards) {
        this.artistCards = artistCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardArtist that = (CardArtist) o;

        if (artistId != that.artistId) return false;
        return artistName.equals(that.artistName);
    }

    @Override
    public int hashCode() {
        int result = artistId;
        result = 31 * result + artistName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardArtist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
