package com.tribesbackend.tribes.JwtAuthToken.config;

import com.tribesbackend.tribes.JwtAuthToken.model.JwtAuthenticationToken;
import com.tribesbackend.tribes.JwtAuthToken.security.JwtAuthenticationEntryPoint;
import com.tribesbackend.tribes.JwtAuthToken.security.JwtAuthenticationProvider;
import com.tribesbackend.tribes.JwtAuthToken.security.JwtAuthenticationTokenFilter;
import com.tribesbackend.tribes.JwtAuthToken.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(new ArrayList<>(Arrays.asList(authenticationProvider)));
    }
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {

        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();

        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/kingdom/**","/logout/**", "/hello").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}
