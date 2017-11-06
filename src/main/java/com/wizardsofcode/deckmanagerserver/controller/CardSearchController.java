/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/12/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardArtist;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardQuery;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchCriteria;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchOperation;
import com.wizardsofcode.deckmanagerserver.model.operations.RestResponse;
import com.wizardsofcode.deckmanagerserver.service.magicdeck.CardSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/card")
public class CardSearchController {


    private CardSearchService searchService;

    private static final Logger logger = LoggerFactory.getLogger(CardSearchController.class);

    @Autowired
    public CardSearchController(CardSearchService searchService){
        this.searchService = searchService;
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> parameterizedSearch(@RequestParam String[] query,
                                                 @RequestParam(defaultValue = "0") String page,
                                                 @RequestParam(defaultValue = "10") String items) throws Exception{

        logger.debug("Method: parameterizedSearch()");

        int pageNumber = Integer.valueOf(page);
        int itemsPerPage = Integer.valueOf(items,10);

        Page result = searchService.parameterizedSearch(readCriterias(query), readConnections(query),
                null, new PageRequest(pageNumber, itemsPerPage));

        ResponseEntity responseEntity = new ResponseEntity<Object>(result, HttpStatus.OK);

        return responseEntity;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> fullTextsearch(@RequestBody CardQuery query){
        logger.debug("Method: fullTextSerch()");
        return new ResponseEntity<Object>(searchService.fullTextSearch(query.getQuery()),HttpStatus.OK);
    }

    @RequestMapping(value = "/search/getcard", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCardById(@RequestParam String cardId){

        logger.debug("Method: getCardById()");
        Card card = searchService.getCardById(Integer.parseInt(cardId));

        if( card == null){
            return new ResponseEntity<Object>(new RestResponse("ResourceNotFound",
                    "The requested card could not be found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(card,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/getall", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<Object>(searchService.retrieveAll(), HttpStatus.OK);
    }

    @JsonView(View.name.class)
    @RequestMapping(value = "/parameters/{parameterName}")
    public ResponseEntity<?> getParameters(@PathVariable String parameterName){
        ResponseEntity response =  null;
        switch (parameterName){
            case "sets":
                break;
            case "layouts":
                break;
            case "rarities":
                break;
            case "types":
                break;
            case "subtypes":
                break;
            case "supertypes":
                break;
        }

        return response;
    }


    private List<SearchCriteria> readCriterias(String[] query){

        List<SearchCriteria> criteriaList = new ArrayList<>();
        int i =0;
        String criteria;
        while (i < query.length){
            if((i % 2) == 0){
                 criteria = query[i];
                if(criteria.contains("!")){
                    criteriaList.add(new SearchCriteria(
                            criteria.substring(0, criteria.indexOf("!")),
                            SearchOperation.NOT_EQUAL,
                            criteria.substring(criteria.indexOf("!") + 1)
                    ));
                }else if(criteria.contains("=")){
                    criteriaList.add(new SearchCriteria(
                            criteria.substring(0, criteria.indexOf("=")),
                            SearchOperation.EQUAL,
                            criteria.substring(criteria.indexOf("=") + 1)
                    ));

                }else if(criteria.contains(">")){
                    criteriaList.add(new SearchCriteria(
                            criteria.substring(0, criteria.indexOf(">")),
                            SearchOperation.GREATER_THAN,
                            criteria.substring(criteria.indexOf(">") + 1)
                    ));

                }else if(criteria.contains("<")){
                    criteriaList.add(new SearchCriteria(
                            criteria.substring(0, criteria.indexOf("<")),
                            SearchOperation.LESS_THAN,
                            criteria.substring(criteria.indexOf("<") + 1)
                    ));

                }else if(criteria.contains("*")){
                    criteriaList.add(new SearchCriteria(
                            criteria.substring(0, criteria.indexOf("*")),
                            SearchOperation.LIKE,
                            criteria.substring(criteria.indexOf("*") + 1)
                    ));

                }
            }
            i++;
        }
        return criteriaList;
    }

    private List<String> readConnections(String[] query){
        List<String> connectorList = new ArrayList<>();
        int i = 0;
        while(i < query.length){
            if(i % 2 != 0){
                String connector = query[i];
                if((connector.equalsIgnoreCase("AND") || connector.equalsIgnoreCase("OR")))
                    connectorList.add(connector);
                else
                    throw new UnsupportedOperationException("The connectors used are not supported by the parametrized search");
            }
            i++;
        }
        return connectorList;
    }

}
