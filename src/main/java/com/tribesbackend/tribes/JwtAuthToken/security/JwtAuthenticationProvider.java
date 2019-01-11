package com.tribesbackend.tribes.JwtAuthToken.security;

import com.tribesbackend.tribes.JwtAuthToken.model.JwtAuthenticationToken;
import com.tribesbackend.tribes.tribesuser.model.MyUserTrPrincipal;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.TribesUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        System.out.println("provider1");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        System.out.println("provider2");
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();
        TribesUser tribesUser = validator.validate(token);
        System.out.println("Provider3");
        if (tribesUser == null) {
            throw new RuntimeException("JWT is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(tribesUser.getRole());
        System.out.println(tribesUser.getRole());
        return new TribesUserDetails(tribesUser.getUsername(),
                token,
                grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
