/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/5/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;


public class PasswordUpdateTransporter {

    private String oldPassword;
    private String newPassword;


    public PasswordUpdateTransporter() {
    }

    public PasswordUpdateTransporter(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
