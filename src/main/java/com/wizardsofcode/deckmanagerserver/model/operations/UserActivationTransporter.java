/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/20/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserActivationTransporter implements Serializable{


    @JsonProperty(required = true)
    private String username;

    @JsonProperty(required = true)
    private String code;

    @JsonProperty(required = true)
    private String password;

    public UserActivationTransporter(String username, String code) {
        this.username = username;
    }

    public UserActivationTransporter() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserActivationTransporter that = (UserActivationTransporter) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
