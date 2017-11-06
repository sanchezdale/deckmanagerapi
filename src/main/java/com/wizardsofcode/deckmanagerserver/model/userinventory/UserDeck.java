/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/23/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.userinventory;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.service.userinventory.DeckSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_deck")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = DeckSerializer.class)
public class UserDeck implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id")
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private Long deckId;

    @Column(name = "deck_name")
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private String deckName;

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonIgnore
    private User owner;

    @Column(name = "is_public", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private boolean isPublic;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "deckId.userDeck")
    @JsonIgnoreProperties("userDeck")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "cards")
    @JsonView(value = {View.deckView.class})
    private List<CardInDeck> cardsInDeck;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private Date created;

    @Transient
    @JsonProperty(value = "owner", access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private String ownerUsername;

    @Transient
    @JsonView(value = {View.Summary.class,View.deckView.class})
    private int numOfCards;


    public UserDeck() {
        this.isDefault = false;
    }

    public UserDeck(String deckName, boolean isPublic) {
        this.deckName = deckName;
        this.isPublic = isPublic;
        this.isDefault = false;
    }

    public UserDeck(boolean isDefault,User owner){
        this.deckName = "Main Deck";
        this.isPublic = false;
        this.isDefault = isDefault;
        this.owner = owner;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonProperty("isPublic")
    public boolean isPublic() {
        return isPublic;
    }

    @JsonProperty("isPublic")
    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public List<CardInDeck> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(List<CardInDeck> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    @JsonView(View.Summary.class)
    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    @JsonView(View.Summary.class)
    public int getNumOfCards() {
        return numOfCards;
    }

    public void setNumOfCards(int numOfCards) {
        this.numOfCards = numOfCards;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    @JsonProperty(value = "owner")
    public String getOwnerusername() {
        return ownerUsername;
    }

    @Column(name = "default_deck", nullable = false)
    public boolean isDefault;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDeck userDeck = (UserDeck) o;

        if (isPublic != userDeck.isPublic) return false;
        if (!deckName.equals(userDeck.deckName)) return false;
        return owner != null ? owner.equals(userDeck.owner) : userDeck.owner == null;
    }

    @Override
    public int hashCode() {
        int result = deckName.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (isPublic ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDeck{" +
                "deckId=" + deckId +
                ", deckName='" + deckName + '\'' +
                ", owner=" + owner +
                ", isPublic=" + isPublic;
    }

    @JsonProperty(value = "default")
    public boolean isDefault() {
        return isDefault;
    }

    @JsonProperty(value = "default")
    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @PostLoad
    protected void onLoad(){
        this.ownerUsername = owner.getUsername();

        for(CardInDeck cid : this.getCardsInDeck())
            this.numOfCards += cid.getCount();
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
}
