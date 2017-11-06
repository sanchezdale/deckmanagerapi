/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/4/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsDAO extends JpaRepository<ClientDetails, String>{



}
