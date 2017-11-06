/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.NamedQuery;

public interface CardDAO extends JpaRepository<Card,Integer>, JpaSpecificationExecutor {

    Page<Card> findByNameLike(Pageable pageable, String name);

    Page<Card> findByCardTextLike(Pageable pageable, String text);

    Page<Card> findByArtistArtistNameLike(Pageable pageable, String artisName);

    Page<Card> findByArtist(Pageable pageable, CardArtist artist);

    Page<Card> findBySetSetNameLike(Pageable pageable, String setname);

    Page<Card> findBySet(Pageable pageable, CardSet set);

    Page<Card> findByRarity(Pageable pageable, CardRarity rarity);

    Page<Card> findByCardManaCosts(Pageable pageable, CardManaCost manaCost);

    Page<Card> findByColorIdentity(Pageable pageable, CardColor color);

    Page<Card> findByLayout(Pageable pageable, CardLayout layout);

    Page<Card> findBySet_SetCode(Pageable pageable, String setcode);

}
