package com.example.demo;

import com.example.demo.master.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {

    @Resource
    UserService userService;
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String cred = authentication.getCredentials().toString();
        User user = userService.loadUserByUsername(name);
        if (!user.isActive()) {
            throw new AuthenticationExcep("user is not active");
        }

        if (!passwordEncoder.matches(cred, user.getPassword())) {
            throw new AuthenticationExcep("password is error");
        }

        return new UsernamePasswordAuthenticationToken(user, cred, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
