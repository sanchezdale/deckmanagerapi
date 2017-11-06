/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/5/17                                 
 */

package com.wizardsofcode.deckmanagerserver.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/verification/publickey").permitAll()
                .antMatchers("/users/resetpassword/**").permitAll()
                .antMatchers("/users/reset/**").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/**").hasAuthority("ROLE_USER")
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/activate/**").permitAll()
                .antMatchers("/card/**").permitAll()
                .antMatchers("/slack/**").permitAll()
                .and().csrf().disable();
    }
}
