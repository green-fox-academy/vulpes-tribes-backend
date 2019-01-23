package com.tribesbackend.tribes.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.configurations.security.SecurityConstants.EXPIRATION_TIME;

public class JWTService {

    public static String createToken (String username){
        String jwtToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    return jwtToken;
    }

    public static String extractUsername (String jwtToken){
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                .build()
                .verify(jwtToken)
                .getSubject();
        return user;
    }
}
