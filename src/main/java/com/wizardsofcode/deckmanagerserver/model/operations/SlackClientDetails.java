/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "slack_clients")
public class SlackClientDetails implements Serializable {


    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slackClientId;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("incoming_webhook")
    @OneToOne(cascade = CascadeType.ALL)
    private SlackWebHook webhook;

    @JsonProperty("bot")
    @OneToOne(cascade = CascadeType.ALL)
    private SlackBot bot;

    public SlackClientDetails() {
    }

    public SlackClientDetails(String accessToken, String scope, String teamName, String teamId, SlackWebHook webhook, SlackBot bot) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.teamName = teamName;
        this.teamId = teamId;
        this.webhook = webhook;
        this.bot = bot;
    }

    public int getSlackClientId() {
        return slackClientId;
    }

    public void setSlackClientId(int slackClientId) {
        this.slackClientId = slackClientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public SlackWebHook getWebhook() {
        return webhook;
    }

    public void setWebhook(SlackWebHook webhook) {
        this.webhook = webhook;
    }

    public SlackBot getBot() {
        return bot;
    }

    public void setBot(SlackBot bot) {
        this.bot = bot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlackClientDetails that = (SlackClientDetails) o;

        if (slackClientId != that.slackClientId) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (webhook != null ? !webhook.equals(that.webhook) : that.webhook != null) return false;
        return bot != null ? bot.equals(that.bot) : that.bot == null;
    }

    @Override
    public int hashCode() {
        int result = slackClientId;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (webhook != null ? webhook.hashCode() : 0);
        result = 31 * result + (bot != null ? bot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SlackClientDetails{" +
                "slackClientId=" + slackClientId +
                ", accessToken='" + accessToken + '\'' +
                ", scope='" + scope + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", webhook=" + webhook +
                ", bot=" + bot +
                '}';
    }
}
