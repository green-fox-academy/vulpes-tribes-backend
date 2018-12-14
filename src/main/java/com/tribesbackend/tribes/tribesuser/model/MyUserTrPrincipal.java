//package com.tribes_backend.tribes.userModel;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class MyUserTrPrincipal implements UserDetails {
//    @Autowired
//    private TribesUser tribesuser;
//
//    public MyUserTrPrincipal(TribesUser tribesuser) {
//        this.tribesuser = tribesuser;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return tribesuser.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return tribesuser.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//
//
//
//
//}
