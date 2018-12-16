package com.tribesbackend.tribes.tribesuser.okstatusservice;

public class OKstatus {

    String status;
    String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OKstatus(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
