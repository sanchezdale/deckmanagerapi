/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_avatars")
public class UserAvatar implements Serializable{


    private static final long serialVersionUID = 13317078581531776L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int avatarId;

    @OneToOne(mappedBy = "avatar")
    @JsonBackReference(value = "user_avatar")
    private User user;

    @Column(name = "picture")
    private String avatar;


    public UserAvatar(User user, String avatar) {
        this.avatar = avatar;
        this.user = user;
    }

    public UserAvatar(){}

    public User getUser() {
        return user;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public void setUser(User user){ this.user = user; }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAvatar that = (UserAvatar) o;

        if (!user.equals(that.user)) return false;
        return avatar.equals(that.avatar);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + avatar.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserAvatar{" +
                "user=" + user +
                ", avatar=" + avatar +
                '}';
    }
}
