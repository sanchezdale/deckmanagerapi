package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wizardsofcode.deckmanagerserver.service.magicdeck.CardSerializer;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "card")
@Entity
@Indexed
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = CardSerializer.class)
@NamedEntityGraph(name = "CardObject", includeAllAttributes = true)
public class Card implements Serializable {


    private static final long serialVersionUID = 5027756626682738213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    @DocumentId
    @JsonView(View.Summary.class)
    private int cardId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "set_id")
    @JsonBackReference("set_card")
    private CardSet set;

    @Column(name = "card_number", columnDefinition = "VARCHAR(10) CHARACTER SET utf8")
    private String cardNumber;

    @Column(name = "card_name", columnDefinition = "VARCHAR(255) CHARACTER SET utf8")
    @Field(store = Store.NO)
    @JsonView(value = {View.Summary.class, View.detailed.class})
    @SortableField
    private String name;

    @Lob
    @Column(name = "text", columnDefinition = "TEXT CHARACTER SET utf8")
    private String cardText;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist")
    @JsonBackReference("artist_card")
    private CardArtist artist;

    @Column(name = "cmc")
    private Integer cmc;

    @Lob
    @Column(name = "flavor", columnDefinition = "TEXT CHARACTER SET utf8")
    private String flavor;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "image_name")
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "layout")
    @JsonBackReference("layout_card")
    private CardLayout layout;

    @Column(name = "mci_number")
    private String mciNumber;

    @Column(name = "multiverse_id")
    private Integer multiverseId;

    @Column(name = "power")
    private String power;

    @Column(name = "toughness")
    private String toughness;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rarity")
    @JsonBackReference("rarity_card")
    private CardRarity rarity;

    @Column(name = "life_modifier")
    private Integer lifeModifier;

    @Column(name = "hand_modifier")
    private Integer handModifier;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cardManaId.cardId")
    @JsonIgnoreProperties("cards")
    private Set<CardManaCost> cardManaCosts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "card_to_type", joinColumns = {@JoinColumn(name = "card_id", nullable = false, updatable = false)},
                inverseJoinColumns = {@JoinColumn(name = "type_id", nullable = false,updatable = false)})
    @JsonIgnoreProperties("cards")
    private Set<CardType> cardTypes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "card_to_subtype", joinColumns = {@JoinColumn(name = "card_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "subtype_id", nullable = false, updatable = false)})
    @JsonIgnoreProperties("cards")
    private Set<CardSubtype> cardSubtypes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "card_to_supertype", joinColumns = {@JoinColumn(name = "card_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "supertype_id", nullable = false, updatable = false)})
    @JsonIgnoreProperties("cards")
    private Set<CardSuperType> cardSuperTypes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "card_color_identity", joinColumns = {@JoinColumn(name = "card_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "mana_color_id", nullable = false, updatable = false)})
    @JsonIgnoreProperties("cards")
    private Set<CardColor> colorIdentity;

    @Transient
    @JsonProperty("count")
    private int countOnDeck;


    public Card() {
        this.countOnDeck = 0;
    }


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public CardSet getSet() {
        return set;
    }

    public void setSet(CardSet set) {
        this.set = set;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public CardArtist getArtist() {
        return artist;
    }

    public void setArtist(CardArtist artist) {
        this.artist = artist;
    }

    public Integer getCmc() {
        return cmc;
    }

    public void setCmc(Integer cmc) {
        this.cmc = cmc;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public CardLayout getLayout() {
        return layout;
    }

    public void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    public String getMciNumber() {
        return mciNumber;
    }

    public void setMciNumber(String mciNumber) {
        this.mciNumber = mciNumber;
    }

    public Integer getMultiverseId() {
        return multiverseId;
    }

    public void setMultiverseId(Integer multiverseId) {
        this.multiverseId = multiverseId;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public CardRarity getRarity() {
        return rarity;
    }

    public void setRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    public Integer getLifeModifier() {
        return lifeModifier;
    }

    public void setLifeModifier(Integer lifeModifier) {
        this.lifeModifier = lifeModifier;
    }

    public Integer getHandModifier() {
        return handModifier;
    }

    public void setHandModifier(Integer handModifier) {
        this.handModifier = handModifier;
    }

    public Set<CardManaCost> getCardManaCosts() {
        return cardManaCosts;
    }

    public void setCardManaCosts(Set<CardManaCost> cardManaCosts) {
        this.cardManaCosts = cardManaCosts;
    }

    public Set<CardType> getCardTypes() {
        return cardTypes;
    }

    public void setCardTypes(Set<CardType> cardTypes) {
        this.cardTypes = cardTypes;
    }

    public Set<CardSubtype> getCardSubtypes() {
        return cardSubtypes;
    }

    @JsonProperty("count")
    public int getCountOnDeck() {
        return countOnDeck;
    }
    @JsonProperty("count")
    public void setCountOnDeck(int countOnDeck) {
        this.countOnDeck = countOnDeck;
    }

    public void setCardSubtypes(Set<CardSubtype> cardSubtypes) {
        this.cardSubtypes = cardSubtypes;
    }

    public Set<com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSuperType> getCardSuperTypes() {
        return cardSuperTypes;
    }

    public void setCardSuperTypes(Set<com.wizardsofcode.deckmanagerserver.model.magicdeck.CardSuperType> cardSuperTypes) {
        this.cardSuperTypes = cardSuperTypes;
    }

    public Set<CardColor> getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(Set<CardColor> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardId != card.cardId) return false;
        if (set != null ? !set.equals(card.set) : card.set != null) return false;
        if (cardNumber != null ? !cardNumber.equals(card.cardNumber) : card.cardNumber != null) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        if (cardText != null ? !cardText.equals(card.cardText) : card.cardText != null) return false;
        if (artist != null ? !artist.equals(card.artist) : card.artist != null) return false;
        if (cmc != null ? !cmc.equals(card.cmc) : card.cmc != null) return false;
        if (flavor != null ? !flavor.equals(card.flavor) : card.flavor != null) return false;
        if (uuid != null ? !uuid.equals(card.uuid) : card.uuid != null) return false;
        if (imageName != null ? !imageName.equals(card.imageName) : card.imageName != null) return false;
        if (layout != null ? !layout.equals(card.layout) : card.layout != null) return false;
        if (mciNumber != null ? !mciNumber.equals(card.mciNumber) : card.mciNumber != null) return false;
        if (multiverseId != null ? !multiverseId.equals(card.multiverseId) : card.multiverseId != null) return false;
        if (power != null ? !power.equals(card.power) : card.power != null) return false;
        if (toughness != null ? !toughness.equals(card.toughness) : card.toughness != null) return false;
        if (rarity != null ? !rarity.equals(card.rarity) : card.rarity != null) return false;
        if (lifeModifier != null ? !lifeModifier.equals(card.lifeModifier) : card.lifeModifier != null) return false;
        if (handModifier != null ? !handModifier.equals(card.handModifier) : card.handModifier != null) return false;
        if (cardManaCosts != null ? !cardManaCosts.equals(card.cardManaCosts) : card.cardManaCosts != null)
            return false;
        if (cardTypes != null ? !cardTypes.equals(card.cardTypes) : card.cardTypes != null) return false;
        if (cardSubtypes != null ? !cardSubtypes.equals(card.cardSubtypes) : card.cardSubtypes != null) return false;
        if (cardSuperTypes != null ? !cardSuperTypes.equals(card.cardSuperTypes) : card.cardSuperTypes != null)
            return false;
        return colorIdentity != null ? colorIdentity.equals(card.colorIdentity) : card.colorIdentity == null;
    }

    @Override
    public int hashCode() {
        int result = cardId;
        result = 31 * result + (set != null ? set.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cardText != null ? cardText.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (cmc != null ? cmc.hashCode() : 0);
        result = 31 * result + (flavor != null ? flavor.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        result = 31 * result + (mciNumber != null ? mciNumber.hashCode() : 0);
        result = 31 * result + (multiverseId != null ? multiverseId.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (toughness != null ? toughness.hashCode() : 0);
        result = 31 * result + (rarity != null ? rarity.hashCode() : 0);
        result = 31 * result + (lifeModifier != null ? lifeModifier.hashCode() : 0);
        result = 31 * result + (handModifier != null ? handModifier.hashCode() : 0);
        result = 31 * result + (cardManaCosts != null ? cardManaCosts.hashCode() : 0);
        result = 31 * result + (cardTypes != null ? cardTypes.hashCode() : 0);
        result = 31 * result + (cardSubtypes != null ? cardSubtypes.hashCode() : 0);
        result = 31 * result + (cardSuperTypes != null ? cardSuperTypes.hashCode() : 0);
        result = 31 * result + (colorIdentity != null ? colorIdentity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", cardText='" + cardText + '\'' +
                ", cmc=" + cmc +
                ", flavor='" + flavor + '\'' +
                ", uuid='" + uuid + '\'' +
                ", imageName='" + imageName + '\'' +
                ", mciNumber='" + mciNumber + '\'' +
                ", multiverseId=" + multiverseId +
                ", power='" + power + '\'' +
                ", toughness='" + toughness + '\'' +
                ", rarity=" + rarity +
                ", lifeModifier=" + lifeModifier +
                ", handModifier=" + handModifier +
                '}';
    }
}
