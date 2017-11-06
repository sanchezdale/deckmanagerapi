/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "group_roles")
public class UserRole implements Serializable,GrantedAuthority{


    private static final long serialVersionUID = 4912676066174726953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = UserAuthority.class, fetch = FetchType.EAGER)
    @JoinTable(name = "role_has_authority",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", nullable = false, updatable = false)})
    @JsonManagedReference(value = "role_authority")
    private Set<UserAuthority> authorities;

    public UserRole(String roleName, Set<UserAuthority> authorities){

        this.roleName = roleName;
        this.authorities = authorities;
    }

    public UserRole(){}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return roleName.equals(userRole.roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
