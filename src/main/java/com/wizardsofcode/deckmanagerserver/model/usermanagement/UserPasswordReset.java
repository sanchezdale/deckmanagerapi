/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user_password_resets")
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class UserPasswordReset implements Serializable{

    private static final long serialVersionUID = -3015129158821711582L;

    // TODO: Suggest using UUID as the id and token at the same time

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "id")
    private UUID passwordResetID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "expires_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date expiresAt;

    @Column(name = "used", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @JsonIgnore
    private boolean used;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * This constructor creates a password reset token based on a user
     * @param user the user to which this token belongs
     */
    public UserPasswordReset(User user) {

        this.user = user;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        createdAt = c.getTime();
        c.add(Calendar.DATE, 1);
        this.expiresAt = c.getTime();
    }

    public UserPasswordReset() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        createdAt = c.getTime();
        c.add(Calendar.DATE, 1);
        this.expiresAt = c.getTime();
    }

    ;

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public UUID getPasswordResetID() {
        return passwordResetID;
    }

    public void setPasswordResetID(UUID passwordResetID) {
        this.passwordResetID = passwordResetID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPasswordReset that = (UserPasswordReset) o;

        if (!passwordResetID.equals(that.passwordResetID)) return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = passwordResetID.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
