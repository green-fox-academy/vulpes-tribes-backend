package com.tribesbackend.tribes.services.responseservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OKstatus {
    @JsonProperty
    String status;
    @JsonProperty("tribes_token")
    String token;

    public OKstatus() {
    }

    public OKstatus(String token) {
        status = "ok";
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
