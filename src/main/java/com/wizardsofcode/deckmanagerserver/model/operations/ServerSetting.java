/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/19/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "server_settings")
public class ServerSetting implements Serializable{


    private static final long serialVersionUID = -6320316262212379019L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int setting_id;

    @Column(name = "setting_name")
    private String settingName;

    @Column(name = "setting_value")
    private String value;


    public ServerSetting() {
    }

    public ServerSetting(String settingName, String value) {
        this.settingName = settingName;
        this.value = value;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerSetting that = (ServerSetting) o;

        if (!settingName.equals(that.settingName)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = settingName.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ServerSettings{" +
                "setting_id=" + setting_id +
                ", settingName='" + settingName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
