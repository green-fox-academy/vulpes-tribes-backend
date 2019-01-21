package com.tribesbackend.tribes.tribesuser.service.errorservice;

public class ResponseService {

    String status;
    String message;

    public ResponseService() {
    }

    public ResponseService(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseService(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ResponseService setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseService setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Message from ResponseService class: " +
                "status='" + status + '\'' +
                ", message='" + message;
    }
}
