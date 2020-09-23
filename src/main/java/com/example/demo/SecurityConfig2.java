package com.example.demo;

import groovy.transform.ASTTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;


@ComponentScan("com.example.demo.master")
@Configuration
@EnableWebSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //下面这两行配置表示在内存中配置了两个用户
//        auth.inMemoryAuthentication()
//                .withUser("javaboy").roles("admin").password("$2a$10$OR3VSksVAmCzc.7WeaRPR.t0wyCsIj24k0Bne8iKWV1o.V9wsP8Xe")
//                .and()
//                .withUser("lisi").roles("user").password("$2a$10$p1H8iWa8I4.CA.7Z8bwLjes91ZpY.rYREGHQEInNtAp4NzL6PLKxi");
//
//        //JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authentication = auth.jdbcAuthentication();
//
//    }

    @Resource
    LoginHandler loginHandler;
    @Resource
    FailureHandler failureHandler;
    @Resource
    LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // AuthenticationSuccessHandler loginHandler = null;
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**.html").permitAll();
        http.httpBasic().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                //.loginProcessingUrl("/login")
                .defaultSuccessUrl("/")

                //.successHandler(loginHandler)
                //.failureHandler(failureHandler)
                .permitAll();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // AuthenticationProvider loginValidateAuthenticationProvider = null;
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

}

