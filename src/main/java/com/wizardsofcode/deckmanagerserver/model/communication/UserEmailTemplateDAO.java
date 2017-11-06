/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/4/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.communication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailTemplateDAO extends JpaRepository<UserEmailTemplate, Integer> {


    UserEmailTemplate findByName(String name);
}
