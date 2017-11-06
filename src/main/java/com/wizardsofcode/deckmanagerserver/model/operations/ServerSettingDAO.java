/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/19/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerSettingDAO extends JpaRepository<ServerSetting,Integer>{

    ServerSetting findBySettingName(String settingName);
}
