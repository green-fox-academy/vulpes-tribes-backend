package com.tribesbackend.tribes.JwtAuthToken.security;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "myBigSecret";

    public TribesUser validate(String token) {
        TribesUser tribesUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            tribesUser = new TribesUser();
            tribesUser.setUsername(body.getSubject());
            tribesUser.setId((Long)body.get("userId"));
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return tribesUser;
    }
}
