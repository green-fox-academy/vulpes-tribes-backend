package com.tribesbackend.tribes.tribesuser.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomTokenService {

    String randomToken;

    public String getRandomToken() {
        return randomToken;
    }

    public void setRandomToken(String randomToken) {
        this.randomToken = randomToken;
    }

    public RandomTokenService() {
        this.randomToken = UUID.randomUUID().toString();
    }
}
