package com.tribes_backend.tribes.controller;

import com.tribes_backend.tribes.model.TribesUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping (value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody TribesUser user) {
        String token = "ksdajasjdasdkjhe912yy8fhaw";
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public TribesUser register() {
        return new TribesUser("newUser");
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

}
