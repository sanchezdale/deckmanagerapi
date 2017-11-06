/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/9/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.magicdeck;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.Card;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchCriteria;
import com.wizardsofcode.deckmanagerserver.model.validation.Password;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardSearchService {


    List<?> fullTextSearch(String searchTerm);

    Page<?> parameterizedSearch(List<SearchCriteria> criteria, List<String> connectors, String sort, Pageable pageable);

    Card getCardById(Integer id);

    Page<Card> retrieveAll();

}
