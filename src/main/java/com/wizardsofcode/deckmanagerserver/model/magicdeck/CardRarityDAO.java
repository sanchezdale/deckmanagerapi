/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRarityDAO extends JpaRepository<CardRarity,Integer> {

    List<CardRarity> findByRarityNameLike(String name);
    Page<CardRarity> findAllByRarityName(Pageable pageable, String name);
    CardRarity findByRarityName(String rarityname);
}
