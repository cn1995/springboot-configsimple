package com.example.demo;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExcep  extends AuthenticationException {
    public AuthenticationExcep(String msg) {
        super(msg);
    }
}
