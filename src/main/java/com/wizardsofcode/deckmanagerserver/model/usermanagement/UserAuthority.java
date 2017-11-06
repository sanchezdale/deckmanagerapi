/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/6/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_permissions")
public class UserAuthority implements Serializable{

    private static final long serialVersionUID = -7745130161981789181L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int authorityId;

    @Column(name = "permission_node")
    private String authorityName;

    public UserAuthority(String authorityName){

        this.authorityName = authorityName;
    }

    public UserAuthority(){}

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthority that = (UserAuthority) o;

        return authorityName.equals(that.authorityName);
    }

    @Override
    public int hashCode() {
        return authorityName.hashCode();
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "AuthorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }

}
