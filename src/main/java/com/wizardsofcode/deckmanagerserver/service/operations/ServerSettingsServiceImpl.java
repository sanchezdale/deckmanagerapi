/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/4/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.operations;

import com.wizardsofcode.deckmanagerserver.model.operations.ServerSetting;
import com.wizardsofcode.deckmanagerserver.model.operations.ServerSettingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerSettingsServiceImpl implements ServerSettingsService {

    private ServerSettingDAO settingDAO;

    @Autowired
    public ServerSettingsServiceImpl(ServerSettingDAO serverSettingDAO){
        this.settingDAO = serverSettingDAO;
    }
    @Override
    public ServerSetting retrieveSetting(String name) {
        return settingDAO.findBySettingName(name);
    }

    @Override
    public boolean updateEmailServerStatus(boolean value) {

        ServerSetting setting = settingDAO.findBySettingName("EMAIL_ACTIVE");
        if(value){
            setting.setValue("true");
        }else
            setting.setValue("false");
        settingDAO.save(setting);
        return true;
    }


}
