/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/8/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck.specification;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.FetchType;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@SuppressWarnings("Duplicates")
public class CardSpecification {

    public static final String notSupportedStrings = "Operators Greater than and less than cannot" +
            " be used with Strings.";

    public static Specification<Card> searchByCardSetName(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardSet> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("setName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("setName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByCardSetCode(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<CardSet, Card> joined = root.join("set", JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("setCode"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("setCode"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByCardArtistName(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardArtist> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("artistName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("artistName"), criteria.value.toString());
                case LIKE:
                    result= cb.like(joined.get("artistName"), "%" + criteria.value.toString() + "%");
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByMultiverseId(SearchCriteria criteria){
        return numericFieldHelper(criteria);
    }

    public static Specification<Card> searchByCardNumber(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }


    public static Specification<Card> searchByCardName(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }

    public static Specification<Card> searchByCardTextContains(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }

    public static Specification<Card> searchByCmc(SearchCriteria criteria){
        return numericFieldHelper(criteria);
    }

    public static Specification<Card> searchByFlavorContains(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }

    public static Specification<Card> searchByCardLayout(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardLayout> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("layoutName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("layoutName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByPower(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }

    public static Specification<Card> searchByToughness(SearchCriteria criteria){
        return stringFileHelper(criteria);
    }

    public static Specification<Card> searchByCardRarity(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardRarity> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("rarityName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("rarityName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByLifeModifier(SearchCriteria criteria){
        return numericFieldHelper(criteria);
    }

    public static Specification<Card> searchByHandModifier(SearchCriteria criteria){
        return numericFieldHelper(criteria);

    }

    public static Specification<Card> searchByCardType(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardType> joined = root.join(criteria.fieldName, JoinType.INNER);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("typeName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("typeName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByCardSupertype(SearchCriteria criteria){
        return (root, query, cb) ->{
            Join<Card,CardSuperType> joined = root.join(criteria.fieldName, JoinType.INNER);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("typeName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("typeName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }


            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByCarManaCost(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardManaCost> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("cardManaId.manaId.manaSymbol"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("cardManaId.manaId.manaSymbol"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByCardSubtypes(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardSubtype> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("subtypeName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("subtypeName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    public static Specification<Card> searchByColorIdentity(SearchCriteria criteria){
        return (root, query, cb) -> {
            Join<Card,CardColor> joined = root.join(criteria.fieldName, JoinType.LEFT);
            Predicate result;
            switch (criteria.operation){
                case NOT_EQUAL:
                    result =  cb.notEqual(joined. <String> get("manaName"), criteria.value.toString());
                    break;
                case EQUAL:
                    result =  cb.equal(joined. <String> get("manaName"), criteria.value.toString());
                    break;
                default:
                    result = null;
                    break;
            }
            query.distinct(true);
            return result;
        };
    }

    private static Specification<Card> stringFileHelper(SearchCriteria criteria){
        return (root, query, cb) -> {
            switch (criteria.operation){
                case LIKE:
                    return cb.like(root.get(criteria.fieldName), "%" + criteria.value.toString() + "%");
                case EQUAL:
                    return cb.equal(root.<String> get(criteria.fieldName), criteria.value.toString());
                case NOT_EQUAL:
                    return cb.notEqual(root.<String> get(criteria.fieldName), criteria.value.toString());
                default:
                    return null;
            }
        };
    }

    private static Specification<Card> numericFieldHelper(SearchCriteria criteria){
        return (root, query, cb) -> {

            switch (criteria.operation){
                case NOT_EQUAL:
                    return cb.notEqual(root.get(criteria.fieldName), criteria.value);
                case EQUAL:
                    return cb.equal(root.get(criteria.fieldName), criteria.value);
                case GREATER_THAN:
                    return cb.gt(root.get(criteria.fieldName), (int) criteria.value);
                case LESS_THAN:
                    return  cb.lt(root.get(criteria.fieldName), (int) criteria.value);
                default:
                    return null;
            }
        };
    }




}
