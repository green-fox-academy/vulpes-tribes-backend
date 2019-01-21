package com.tribesbackend.tribes.tribesuser.service.errorservice;

public class ErrorResponseModel extends ResponseService {

    public ErrorResponseModel(String errorMessage) {
        super("error", errorMessage);
    }
}
