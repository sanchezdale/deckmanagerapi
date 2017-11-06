/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.service.operations;

import com.wizardsofcode.deckmanagerserver.model.operations.ServerSetting;
import org.apache.catalina.Server;

public interface ServerSettingsService {

    ServerSetting retrieveSetting(String name);

    boolean updateEmailServerStatus(boolean setting);
}
