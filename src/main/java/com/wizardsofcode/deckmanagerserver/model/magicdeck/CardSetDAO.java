/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSetDAO extends JpaRepository<CardSet,Integer>{

    Page<CardSet> findBySetNameLike(Pageable pageable, String name);
    CardSet findBySetCode(String setcode);
}
