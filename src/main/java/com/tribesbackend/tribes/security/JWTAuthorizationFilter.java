package com.tribesbackend.tribes.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.tribesbackend.tribes.security.SecurityConstants.HEADER_STRING;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if (header == null) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        }catch (JWTDecodeException e ){
            res.setStatus(403);
            PrintWriter out = res.getWriter();
            ObjectMapper objectMapper= new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(new ErrorResponseModel("Invalid token"));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.

            String user = JWTService.extractUsername(token);
//
//            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
//                    .build()
//                    .verify(token)
//                    .getSubject();

            System.out.println("USER IS:::::::::::::::::::::" + user);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

            }
        }
        return null;
    }
}

