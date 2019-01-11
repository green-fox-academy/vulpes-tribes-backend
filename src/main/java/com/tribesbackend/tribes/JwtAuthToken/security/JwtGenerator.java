package com.tribesbackend.tribes.JwtAuthToken.security;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(TribesUser user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));


                return Jwts.builder()
                .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "myBigSecret")
                        .compact();

    }
}
