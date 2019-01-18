package com.tribesbackend.tribes.tribesuser.model;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserTrPrincipal implements UserDetails {

    private TribesUser tribesuser;

    Kingdom kingdom;

    public MyUserTrPrincipal(TribesUser tribesuser) {
        this.tribesuser = tribesuser;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
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
}
