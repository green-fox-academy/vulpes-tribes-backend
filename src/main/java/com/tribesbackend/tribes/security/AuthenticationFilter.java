package com.tribesbackend.tribes.security;


import com.tribesbackend.tribes.tribesuser.okstatusservice.RandomToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter implements Filter {

    RandomToken randomToken;

    public AuthenticationFilter (RandomToken randomToken) {this.randomToken = randomToken;}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Optional<String> token = Optional.ofNullable( httpRequest.getHeader("MyToken") );

        SecurityContext context = SecurityContextHolder.getContext();

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication a = SecurityContextHolder.getContext().getAuthentication();
        }
    }

    @Override
    public void destroy() {
    }
}
