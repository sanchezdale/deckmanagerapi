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

@Repository
public interface CardArtistDAO extends JpaRepository<CardArtist,Integer>,
        JpaSpecificationExecutor{

    Page<CardArtist> findByArtistNameLike(Pageable page, String name);
    CardArtist findByArtistNameEquals(String artistName);

}
