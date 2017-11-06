/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackCommand {

    private String token,teamId,
    teamDomain,enterpriseId, enterpriseName,
    channelId, channelName, userId, userName,
    command, text,responseUrl,triggerId;

    @JsonProperty(value = "token")
    public String getToken() {
        return token;
    }

    @JsonProperty(value = "token")
    public void setToken(String token) {
        this.token = token;
    }

    public String getTeamId() {
        return teamId;
    }

    @JsonProperty(value = "team_id")
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamDomain() {
        return teamDomain;
    }
    @JsonProperty(value = "team_domain")
    public void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }
    @JsonProperty(value = "enterprise_id")
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }
    @JsonProperty(value = "enterprise_name")
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getChannelId() {
        return channelId;
    }
    @JsonProperty(value = "channel_id")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }
    @JsonProperty(value = "channel_name")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUserId() {
        return userId;
    }
    @JsonProperty(value = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    @JsonProperty(value = "user_name")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommand() {
        return command;
    }
    @JsonProperty(value = "command")
    public void setCommand(String command) {
        this.command = command;
    }

    public String getText() {
        return text;
    }
    @JsonProperty(value = "text")
    public void setText(String text) {
        this.text = text;
    }

    public String getResponseUrl() {
        return responseUrl;
    }
    @JsonProperty(value = "response_url")
    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getTriggerId() {
        return triggerId;
    }
    @JsonProperty(value = "trigger_id")
    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public SlackCommand() {


    }
}
