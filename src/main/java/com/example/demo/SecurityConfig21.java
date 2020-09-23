package com.example.demo;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class SecurityConfig21 extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring().requestMatchers(PathRequest.toH2Console());
    }
    // @formatter:on

    // @formatter:off
    // tag::config[]
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((authorize) -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                        .permitAll()
                );
    }
    // end::config[]
    // @formatter:on

}
