/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/18/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "slack_bot")
public class SlackBot implements Serializable{


    @JsonProperty("bot_user_id")
    @Id
    private String botUserId;

    @JsonProperty("bot_access_token")
    private String botAccessToken;

    @OneToOne(mappedBy = "bot")
    private SlackClientDetails slackClientDetail;

    public SlackBot() {
    }

    public SlackBot(String botUserId, String botAccessToken) {
        this.botUserId = botUserId;
        this.botAccessToken = botAccessToken;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    public String getBotAccessToken() {
        return botAccessToken;
    }

    public void setBotAccessToken(String botAccessToken) {
        this.botAccessToken = botAccessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlackBot slackBot = (SlackBot) o;

        if (botUserId != null ? !botUserId.equals(slackBot.botUserId) : slackBot.botUserId != null) return false;
        return botAccessToken != null ? botAccessToken.equals(slackBot.botAccessToken) : slackBot.botAccessToken == null;
    }

    @Override
    public int hashCode() {
        int result = botUserId != null ? botUserId.hashCode() : 0;
        result = 31 * result + (botAccessToken != null ? botAccessToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SlackBot{" +
                "botUserId='" + botUserId + '\'' +
                ", botAccessToken='" + botAccessToken + '\'' +
                '}';
    }
}
