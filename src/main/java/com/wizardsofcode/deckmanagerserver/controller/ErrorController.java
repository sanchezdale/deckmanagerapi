/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/4/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController {


    @RequestMapping("/")
    public ResponseEntity<?> home(){
        return new ResponseEntity<Object>("This endpoint is not accesible", HttpStatus.OK);
    }
}
