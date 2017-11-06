/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users_activation")
public class UserActivation implements Serializable{

    private static final long serialVersionUID = 7159239399134408755L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "id")
    private UUID activationId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_activation")
    private User user;


    private String url;



    public UserActivation(User user) {
        this.user = user;
    }

    public UserActivation(){}

    public User getUser() {
        return user;
    }

    public UUID getActivationId() {
        return activationId;
    }

    public void setActivationId(UUID activationId) {
        this.activationId = activationId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @PrePersist
    private void setUrl(){ this.url = "Not set"; }



}
