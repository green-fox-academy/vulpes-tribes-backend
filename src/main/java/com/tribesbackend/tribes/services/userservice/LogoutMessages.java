package com.tribesbackend.tribes.services.userservice;
//Class to delete, only for testing without proper /logout endpoint
public class LogoutMessages {
    String status;
    String message;

    public LogoutMessages() {
    }

    public LogoutMessages(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public LogoutMessages setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }
}
