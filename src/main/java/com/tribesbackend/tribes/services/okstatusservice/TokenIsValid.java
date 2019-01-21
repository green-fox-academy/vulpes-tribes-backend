package com.tribesbackend.tribes.services.okstatusservice;

public class TokenIsValid {

    public static boolean isValid(String token){
        if (token.equals("1")){
            return true;
        } else return false;
    }
}
