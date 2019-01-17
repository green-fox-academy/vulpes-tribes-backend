package com.tribesbackend.tribes.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{






    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //     .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/kingdom/**","/user/**").authenticated()
                //  .antMatchers("/list").authenticated()
                // .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));


    }


}
