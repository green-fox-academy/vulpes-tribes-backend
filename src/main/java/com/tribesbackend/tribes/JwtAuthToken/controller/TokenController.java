package com.tribesbackend.tribes.JwtAuthToken.controller;

import com.tribesbackend.tribes.JwtAuthToken.security.JwtGenerator;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping
    public String generate(@RequestBody final TribesUser user) {
        JwtGenerator jwtGenerator = new JwtGenerator();
        jwtGenerator.generate(user)
    }
}
