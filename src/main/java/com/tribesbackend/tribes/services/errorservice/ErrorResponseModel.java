package com.tribesbackend.tribes.services.errorservice;

public class ErrorResponseModel extends ResponseService {

    public ErrorResponseModel(String errorMessage) {
        super("error", errorMessage);
    }
}
