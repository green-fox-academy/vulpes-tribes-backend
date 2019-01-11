package com.tribesbackend.tribes.tribesuser.model;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class MyUserTrPrincipal implements UserDetails {

    private TribesUser tribesuser;
    private String token;



    public MyUserTrPrincipal(TribesUser tribesuser) {
        this.tribesuser = tribesuser;
    }

    public MyUserTrPrincipal(TribesUser tribesuser, String token) {
        this.tribesuser = tribesuser;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return tribesuser.getPassword();
    }

    @Override
    public String getUsername() {
        return tribesuser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }
}
