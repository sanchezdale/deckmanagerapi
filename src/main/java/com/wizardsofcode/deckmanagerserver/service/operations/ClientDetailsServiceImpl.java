/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/4/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.operations;

import com.wizardsofcode.deckmanagerserver.model.operations.ClientDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.HashSet;
import java.util.Set;

public class ClientDetailsServiceImpl {


    private ClientDetailsDAO clientDetailsDAO;

    @Autowired
    public ClientDetailsServiceImpl(ClientDetailsDAO cientDetailsDAO){
        this.clientDetailsDAO = clientDetailsDAO;
    }


    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        com.wizardsofcode.deckmanagerserver.model.operations.ClientDetails details = clientDetailsDAO.findOne(clientId);


        details.setScoped(true);

        Set<String> scopes = new HashSet<>();



        return null;
    }
}
