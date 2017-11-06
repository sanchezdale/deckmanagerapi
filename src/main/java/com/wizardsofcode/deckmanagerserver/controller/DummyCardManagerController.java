/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/30/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import com.wizardsofcode.deckmanagerserver.model.magicdeck.CardDAO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


//TODO: Delete after implementation
@RestController
@RequestMapping("/dummy")
public class DummyCardManagerController {

    @Autowired
    CardDAO dao;


    @RequestMapping(value = "/cards", produces = "application/json", method = RequestMethod.GET)
    public void returnDummyCards(HttpServletResponse response){

        ClassPathResource cpr = new ClassPathResource("test_json.json");

        try{
            IOUtils.copy(cpr.getInputStream(),response.getOutputStream());
        }catch (Exception e){
            //Do nothing
        }
    }

    @RequestMapping(value = "/paged", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> returnPagedCards(){
        return new ResponseEntity<Object>(dao.findAll(new PageRequest(0,10)), HttpStatus.OK);
    }
}
