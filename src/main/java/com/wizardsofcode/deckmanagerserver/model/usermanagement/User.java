/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */

package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import com.fasterxml.jackson.annotation.*;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import com.wizardsofcode.deckmanagerserver.model.userinventory.UserDeck;
import org.hibernate.annotations.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Table(name="users", uniqueConstraints = {@UniqueConstraint(name = "username" , columnNames = "username")})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"userId","username","firstName","lastName","phoneNumber","alternativeNumber","avatar","email","defaultDeck"})
public class User implements Serializable {


    private static final long serialVersionUID = -6272494138743673755L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    @Column(name = "username", length = 32, unique = true, nullable = false)
    @JsonProperty(required = true)
    @JsonView(value = View.Summary.class)
    private String username;

    @Column(length = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "first_name", length = 16)
    private String firstName;

    @Column(name = "last_name", length = 16)
    private String lastName;

    @OneToOne(targetEntity = UserEmail.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    @JsonManagedReference(value = "user_email")
    private UserEmail email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn( name = "avatar")
    @JsonManagedReference(value = "user_avatar")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped
    private UserAvatar avatar;

    @Column(name = "main_phone_number")
    private String phoneNumber;

    @Column(name = "alternative_number")
    private String alternativeNumber;

    @ManyToMany(targetEntity = UserRole.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_roles",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)} ,
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
    @JsonIgnore
    private List<UserRole> userRoles;

    @Column(name = "active", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;


    @OneToOne(mappedBy = "user", targetEntity = UserActivation.class)
    @JsonManagedReference(value = "user_activation")
    private UserActivation userActivation;

    @Transient
    @JsonIgnore
    private boolean passwordUpdated;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("owner")
    @JsonIgnore
    private Set<UserDeck> decks;

    @Transient
    private UserDeck defaultDeck;



    /**
     * Constructor with required values. Initializes avatar value to default image
     *
     * @param username the username of the user
     * @param password Temporary field foe the non-encrypted version of the password
     * @param first_name User's first name
     */
    public User( String username, String password, String first_name) {
        this.username = username;
        this.password = password;
        this.firstName = first_name;
        this.active = false;
        this.passwordUpdated = false;

        //Implementation of default avatar coming soon
    }

    /**
     * Default Constructor
     */
    public User() {
        active = false;
        this.passwordUpdated = false;
    }

    // accessor or mutator for some properties were removed accounting operations that cannot be done


    public long getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username){
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword(){ return password; }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String last_name) {
        this.lastName = last_name;
    }


    public UserEmail getEmail() {
        return email;
    }


    public void setEmail(UserEmail email) {
        this.email = email;
    }


    public String getPhoneNumber(){ return phoneNumber; }


    public void setPhoneNumber(String phoneNumbers){ this.phoneNumber = phoneNumbers; }

    public UserDeck getDefaultDeck() {
        return defaultDeck;
    }

    public void setDefaultDeck(UserDeck defaultDeck) {
        this.defaultDeck = defaultDeck;
    }

    @JsonIgnore
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(UserAvatar avatar) {
        this.avatar = avatar;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public UserActivation getUserActivation() {
        return userActivation;
    }

    public void setUserActivation(UserActivation userActivation) {
        this.userActivation = userActivation;
    }

    public boolean isPasswordUpdated() {
        return passwordUpdated;
    }

    public void setPasswordUpdated(boolean passwordUpdated) {
        this.passwordUpdated = passwordUpdated;
    }

    public Set<UserDeck> getDecks() {
        return decks;
    }

    public void setDecks(Set<UserDeck> decks) {
        this.decks = decks;
    }

    @PrePersist
    private void encryptPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        this.password = passwordEncoder.encode(getPassword());
    }

    public String getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }



    @PreUpdate
    private void encryptPasswordAfterUpdate() {

        if (passwordUpdated) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            this.password = passwordEncoder.encode(getPassword());
            passwordUpdated = false;
        }else {

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (active != user.active) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                '}';
    }

}
