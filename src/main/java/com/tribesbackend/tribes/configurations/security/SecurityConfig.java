package com.tribesbackend.tribes.configurations.security;
import com.tribesbackend.tribes.services.userservice.MyUserTrDetailsService;
import com.tribesbackend.tribes.tribesuser.okstatusservice.RandomToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    RandomToken randomToken;
    @Autowired
    MyUserTrDetailsService myUserTrDetailsService;

    @Override
    protected void configure (AuthenticationManagerBuilder auth)
            throws  Exception{
        auth.authenticationProvider( authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider
                = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService( myUserTrDetailsService );
        authenticationProvider.setPasswordEncoder( encoder());
        return authenticationProvider;
    }

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

                //.formLogin().and()
              //  .httpBasic()
              //  .and()
              //  .logout();

        //    http.addFilterAfter(new AuthenticationFilter(tokenService), BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
