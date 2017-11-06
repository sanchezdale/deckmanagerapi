/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.cardmanagement;

import com.wizardsofcode.deckmanagerserver.config.DatabaseConfig;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.*;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.CardSpecification;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchCriteria;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.specification.SearchOperation;
import com.wizardsofcode.deckmanagerserver.service.magicdeck.CardSearchService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@ComponentScan(value = {"com.wizardsofcode.deckmanagerserver"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {DatabaseConfig.class})
public class TestCardManagement {

    @Autowired CardDAO cardDao;
    @Autowired
    CardSearchService searchService;

    private static Card cardOld;
    private static Card cardOld2;

    @BeforeClass
    public static void loadDataset(){

    }
    @Test
    public void starTest(){
        //cardDao.save(cardOld);
        //cardDao.save(cardOld2);
    }

    @Test
    public void checkCriteria(){
        List<SearchCriteria> criteria = new ArrayList<>();
        criteria.add(new SearchCriteria("setCode",SearchOperation.EQUAL,"UHN"));
        //criteria.add(new SearchCriteria("cardSubtypes",SearchOperation.EQUAL,"Human"));

        List<String> operations = new ArrayList<>();
        //operations.add("AND");

        Page cards = searchService.parameterizedSearch(criteria,operations,null,new PageRequest(0,10));

        Assert.assertEquals(true,true);
    }

}
