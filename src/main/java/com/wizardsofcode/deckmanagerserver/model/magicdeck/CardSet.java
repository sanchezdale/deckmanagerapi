package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "card_set")
@Entity
@Indexed
public class CardSet implements Serializable {

    private static final long serialVersionUID = -4877243971558298701L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private int setId;

    @Column(name = "name")
    @Field
    @JsonView(View.name.class)
    private String setName;

    @Column(name = "code", unique = true)
    @Field
    private String setCode;

    @Column(name = "count")
    private int count;

    @OneToMany(mappedBy = "set", fetch = FetchType.EAGER)
    @IndexedEmbedded(includeEmbeddedObjectId = true)
    @JsonManagedReference("set_card")
    private List<Card> cards;

    public CardSet() {
    }

    public CardSet(String setName, String setCode, int count) {
        this.setName = setName;
        this.setCode = setCode;
        this.count = count;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

        CardSet cardSet = (CardSet) o;

        if (setId != cardSet.setId) return false;
        if (count != cardSet.count) return false;
        if (!setName.equals(cardSet.setName)) return false;
        return setCode.equals(cardSet.setCode);
    }

    @Override
    public int hashCode() {
        int result = setId;
        result = 31 * result + setName.hashCode();
        result = 31 * result + setCode.hashCode();
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "setId=" + setId +
                ", setName='" + setName + '\'' +
                ", setCode='" + setCode + '\'' +
                ", count=" + count +
                '}';
    }
}

