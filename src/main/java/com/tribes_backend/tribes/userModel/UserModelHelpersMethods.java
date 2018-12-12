package com.tribes_backend.tribes.userModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelHelpersMethods {


    public UserModelHelpersMethods() {
    }

    public boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        } else return true;
    }
}
