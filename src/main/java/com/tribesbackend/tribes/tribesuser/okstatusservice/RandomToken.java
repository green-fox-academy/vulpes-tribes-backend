package com.tribesbackend.tribes.tribesuser.okstatusservice;

import java.util.UUID;

public class RandomToken {

    String randomToken;

    public String getRandomToken() {
        return randomToken;
    }

    public void setRandomToken(String randomToken) {
        this.randomToken = randomToken;
    }

    public RandomToken() {
        this.randomToken = UUID.randomUUID().toString();
    }
}
