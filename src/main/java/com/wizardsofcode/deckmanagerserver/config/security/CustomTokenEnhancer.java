/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/29/17                                 
 */

package com.wizardsofcode.deckmanagerserver.config.security;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    Environment env;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("iss", env.getProperty("baseurl"));
        additionalInfo.put("iat", new Date().toString());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        //TODO: change expiration
        //TODO: add user endpoint link
        return accessToken;
    }
}
