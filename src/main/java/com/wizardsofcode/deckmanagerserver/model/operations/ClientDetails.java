/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/3/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.operations;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "client_details")
public class ClientDetails implements org.springframework.security.oauth2.provider.ClientDetails {


    @Id
    @Column(name = "client_id", unique = true)
    private String clientId;

    @Transient
    private Set<String> resourceIds;

    private boolean secretRequired;

    private String clientSecret;

    @Transient
    private boolean scoped;

    @Transient
    private Set<String> scopes;

    @Transient
    private Set<String> authorizedGrantTypes;

    @Transient
    private Set<String> registereURIs;

    @Transient
    private Set<GrantedAuthority> grantedAuthorities;


    private int tokenValidity;


    private int refreshTokenValidity;

    @Transient
    private Map<String,Object> additionalInfo;


    private String serviceUrl;


    public ClientDetails() {
        SecureRandom random = new SecureRandom();

        StringBuilder builder = new StringBuilder();

        int i = 0;

        for (;i < 8; i++){

            char letter = (char)((random.nextInt() % 122) + 65);
            builder.append(letter);
        }

        clientId = builder.toString();


        for (i = 0;i < 10; i++){

            builder.append(random.nextInt(9));
        }
    }

    public ClientDetails(String serviceUrl) {


    }

    @Override
    public String getClientId() {
        return null;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return scoped;
    }

    @Override
    public Set<String> getScope() {
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registereURIs;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return tokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInfo;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScoped(boolean scoped) {
        this.scoped = scoped;
    }

    public void setTokenValidity(int tokenValidity) {
        this.tokenValidity = tokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
