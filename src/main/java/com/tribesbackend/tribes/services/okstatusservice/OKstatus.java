package com.tribesbackend.tribes.services.okstatusservice;

import com.tribesbackend.tribes.models.Kingdom;

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

    public OKstatus(String ok, Kingdom kingdomByTribesUserUsername) {
    }
//
//    public OKstatus(String ok, Optional<Building>findAllByTribesUserUsername){
//    }
//
//    public OKstatus(String ok, Optional<ResourcesModel>findAllByTribesUserUsername){
//    }
}
