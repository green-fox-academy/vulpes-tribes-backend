package com.tribes_backend.tribes.kingdomModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserTrPrincipal implements UserDetails {

    private TribesUser tribesUser;

    public MyUserTrPrincipal(TribesUser tribesUser) {
        this.tribesUser = tribesUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return tribesUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tribesUser.getUsername();
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
