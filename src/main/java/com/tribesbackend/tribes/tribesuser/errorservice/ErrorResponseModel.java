package com.tribesbackend.tribes.tribesuser.errorservice;

public class ErrorResponseModel {
    String status;
    String errorMessage;

    public ErrorResponseModel() {
    }

    public ErrorResponseModel(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String error) {
        this.status = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error Message from ErrorResponseModel class: " +
                "error='" + status + '\'' +
                ", errorMessage='" + errorMessage;
    }
}
