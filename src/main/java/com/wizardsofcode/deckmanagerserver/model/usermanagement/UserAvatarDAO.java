/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAvatarDAO extends CrudRepository<UserAvatar,Integer>{

}
