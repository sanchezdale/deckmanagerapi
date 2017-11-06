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
public class SlackWebHook implements Serializable{


    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String channel;

    @JsonProperty("configuration_url")
    private String configurationUrl;

    @OneToOne(mappedBy = "webhook")
    private SlackClientDetails slackClientDetail;

    public SlackWebHook() {
    }

    public SlackWebHook(String url, String channel, String configurationUrl) {
        this.url = url;
        this.channel = channel;
        this.configurationUrl = configurationUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getConfigurationUrl() {
        return configurationUrl;
    }

    public void setConfigurationUrl(String configurationUrl) {
        this.configurationUrl = configurationUrl;
    }

    @Override
    public String toString() {
        return "SlackWebHook{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", channel='" + channel + '\'' +
                ", configurationUrl='" + configurationUrl + '\'' +
                '}';
    }

    public SlackClientDetails getSlackClientDetail() {
        return slackClientDetail;
    }

    public void setSlackClientDetail(SlackClientDetails slackClientDetail) {
        this.slackClientDetail = slackClientDetail;
    }
}
