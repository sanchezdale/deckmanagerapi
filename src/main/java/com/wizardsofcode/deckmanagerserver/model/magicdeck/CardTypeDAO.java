/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardTypeDAO extends JpaRepository<CardType,Integer>, JpaSpecificationExecutor{

    List<CardType> findByTypeNameLike(String name);
    Page<CardType> findAllByTypeName(Pageable pageable, String cardtypename);
    CardType findByTypeName(String cardtypename);
}
