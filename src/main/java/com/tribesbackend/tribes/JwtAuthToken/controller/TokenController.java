package com.tribesbackend.tribes.JwtAuthToken.controller;

import com.tribesbackend.tribes.JwtAuthToken.security.JwtGenerator;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final TribesUser user) {
        return jwtGenerator.generate(user);
    }
}
