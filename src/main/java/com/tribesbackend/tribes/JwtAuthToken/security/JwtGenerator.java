package com.tribesbackend.tribes.JwtAuthToken.security;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtGenerator {
    public String generate(TribesUser user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("password", user.getPassword())


                Jwts.builder()

                .setClaims(claims)
    }
}
