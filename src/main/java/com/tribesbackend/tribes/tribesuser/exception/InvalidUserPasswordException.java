package com.tribesbackend.tribes.tribesuser.exception;

public class InvalidUserPasswordException extends RuntimeException {
    String errortype;

    public InvalidUserPasswordException() {
    }

    public String getErrortype() {
        return errortype;
    }

    public void setErrortype(String errortype) {
        this.errortype = errortype;
    }


    public InvalidUserPasswordException(String errorType, String errorMessage) {
        super(errorMessage);
       this.errortype = errorType;
//       this.errorMessage = errorMessage;
    }
}
