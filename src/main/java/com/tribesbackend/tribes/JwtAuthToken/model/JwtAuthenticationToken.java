package com.tribesbackend.tribes.JwtAuthToken.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
