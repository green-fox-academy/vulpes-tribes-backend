package com.tribesbackend.tribes.services.responseservice;

public class OKstatus extends ResponseService {

    String token;

    public OKstatus() {
    }

    public OKstatus(String token) {
        super("ok");
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
