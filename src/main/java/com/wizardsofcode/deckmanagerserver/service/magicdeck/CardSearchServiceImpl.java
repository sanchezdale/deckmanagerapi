/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/9/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardDAO;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.CardSpecification;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchCriteria;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardSearchServiceImpl implements CardSearchService {


    private CardDAO cardDao;
    private EntityManager entityManager;

    @Autowired
    public CardSearchServiceImpl(CardDAO cardDAO, EntityManager entityManager)throws Exception{
        this.entityManager = entityManager;
        this.cardDao = cardDAO;
    }

    @Override
    public List<?> fullTextSearch(String searchTerm) {

        final FullTextEntityManager em = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = em.getSearchFactory().buildQueryBuilder().forEntity(Card.class).get();

        org.apache.lucene.search.Query query = queryBuilder.bool().should(queryBuilder.keyword().onField("name").matching(searchTerm)
                .createQuery()).createQuery();

        javax.persistence.Query jpaQuery = em.createFullTextQuery(query,Card.class).setSort(new Sort(SortField.FIELD_SCORE,new SortField("name", SortField.Type.STRING)));
        return jpaQuery.getResultList();
    }

    @Override
    public Page<Card> parameterizedSearch(List<SearchCriteria> criteria, List<String> connectors,String sort ,Pageable pageable){

        Specification<Card> currentSpec = null;

        List<Specification> specs = new ArrayList<>();
        int i = 0;
        for(; i < criteria.size(); i++){
            specs.add(extractFieldToUse(criteria.get(i)));
        }

        if (specs.get(0) == null){
            return null;
        }

        Specifications spec = Specifications.where(specs.get(0));

        for(i = 1; i < specs.size(); i++){
            if(connectors.get( i - 1).equalsIgnoreCase("AND")){
                spec = spec.and(specs.get(i));
            }else {
                spec = spec.or(specs.get(i));
            }
        }

        return cardDao.findAll(spec, pageable);
    }


    private Specification<Card> extractFieldToUse(SearchCriteria criteria) throws UnsupportedOperationException{

        switch (criteria.fieldName){
            case "name":
                return CardSpecification.searchByCardName(criteria);
            case "set":
                return CardSpecification.searchByCardSetName(criteria);
            case "setCode":
                return CardSpecification.searchByCardSetCode(criteria);
            case "cardNumber":
                return CardSpecification.searchByCardNumber(criteria);
            case "cardText":
                return CardSpecification.searchByCardTextContains(criteria);
            case "artist":
                return CardSpecification.searchByCardArtistName(criteria);
            case "cmc":
                return CardSpecification.searchByCmc(criteria);
            case "flavor":
                return CardSpecification.searchByFlavorContains(criteria);
            case "layout":
                return CardSpecification.searchByCardLayout(criteria);
            case "power":
                return CardSpecification.searchByPower(criteria);
            case "toughness":
                return CardSpecification.searchByToughness(criteria);
            case "rarity":
                return CardSpecification.searchByCardRarity(criteria);
            case "lifeModifier":
                return CardSpecification.searchByLifeModifier(criteria);
            case "handModifier":
                return CardSpecification.searchByHandModifier(criteria);
            case "cardManaCosts":
                return CardSpecification.searchByCarManaCost(criteria);
            case "cardTypes":
                return CardSpecification.searchByCardType(criteria);
            case "cardSubtypes":
                return CardSpecification.searchByCardSubtypes(criteria);
            case "cardSupertype":
                return CardSpecification.searchByCardSupertype(criteria);
            case "colorIdentity":
                return CardSpecification.searchByColorIdentity(criteria);
            case "multiverseId":
                return CardSpecification.searchByMultiverseId(criteria);
            default:
                throw new UnsupportedOperationException("The field supplied cannot be recognized (" + criteria.fieldName +") please read the documentation");
        }
    }

    @Override
    public Card getCardById(Integer id){
        return cardDao.findOne(id);
    }

    @Override
    public Page<Card> retrieveAll() {
        return cardDao.findAll(new PageRequest(0,20));
    }


}
