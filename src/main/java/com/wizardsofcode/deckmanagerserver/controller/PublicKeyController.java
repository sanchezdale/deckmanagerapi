/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/30/17                                 
 */

package com.wizardsofcode.deckmanagerserver.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/verification")
public class PublicKeyController {

    private static final ClassPathResource publicKey = new ClassPathResource("public.txt");

    @ResponseBody
    @RequestMapping("/publickey")
    public void getPublicKey(HttpServletResponse response) throws Exception{

        response.setContentType("text/plain");

        IOUtils.copy(publicKey.getInputStream(), response.getOutputStream());
    }
}
