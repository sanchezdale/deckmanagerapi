package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "card_supertype", uniqueConstraints =
        {@UniqueConstraint(name = "idx_supertype_name", columnNames = "supertype_name")})
@Entity
public class CardSuperType implements Serializable {


    private static final long serialVersionUID = 7974194682087174841L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supertype_id")
    private int typeId;

    @Column(name = "supertype_name")
    @JsonView(View.name.class)
    private String typeName;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cardSuperTypes")
    @JsonIgnoreProperties("cardSupertypes")
    private List<Card> cards;

    public CardSuperType() {

    }

    public CardSuperType(String type) {
        this.typeName = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return typeName;
    }

    public void setType(String type) {
        this.typeName = type;
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

        CardSuperType that = (CardSuperType) o;

        if (typeId != that.typeId) return false;
        return typeName.equals(that.typeName);
    }

    @Override
    public int hashCode() {
        int result = typeId;
        result = 31 * result + typeName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardSuperType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
