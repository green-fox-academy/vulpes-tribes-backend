package com.tribesbackend.tribes.tribesuser.exception;

public class TribesError {
    String status;
    String message;

    public TribesError() {
    }

    public TribesError(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
