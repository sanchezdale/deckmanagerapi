/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_emails")
public class UserEmail implements Serializable {

    private static final long serialVersionUID = 5604170218029058891L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emailId;

    @OneToOne(mappedBy = "email", fetch = FetchType.EAGER, targetEntity = User.class)
    @JsonBackReference(value = "user_email")
    private User user;

    @Column(name = "email", unique = true)
    private String email;

    public UserEmail(String email){
        this.email = email;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public UserEmail(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEmail userEmail = (UserEmail) o;

        return email != null ? email.equals(userEmail.email) : userEmail.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserEmail{" +
                ", email='" + email + '\'' +
                '}';
    }


}
