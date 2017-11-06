/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import org.springframework.data.repository.CrudRepository;

public interface SlackClientsDAO extends CrudRepository<SlackClientDetails, Integer> {

    SlackClientDetails findByTeamId(String teamId);
}
