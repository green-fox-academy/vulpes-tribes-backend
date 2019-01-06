package com.tribesbackend.tribes.tribesuser.okstatusservice;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTCreator {
//1a
String jwt = Jwts.builder()
        .setClaims(claim)
        .setSubject("email")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+ JWT_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
        .compact();
// 1]
    String jwt = Jwts.builder()
            .setSubject("users/TzMUocMF4p")
            .setExpiration(new Date(1300819380))
            .claim("name", "Robert Token Man")
            .claim("scope", "self groups/admins")
            .signWith(
                    SignatureAlgorithm.HS256,
                    "secret".getBytes("UTF-8")
            )
            .compact();

//2]
    public static  String createJWT(String id, String issuer, String subject, long ttlMillis) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}



