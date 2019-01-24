package com.tribesbackend.tribes.factories;

import com.auth0.jwt.JWT;
import com.tribesbackend.tribes.configurations.security.SecurityConstants;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.configurations.security.SecurityConstants.EXPIRATION_TIME;

public class TokenFactory {

    public static String createValidToken() {
        return JWT.create()
                .withSubject("Vojtisek")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    }
}
