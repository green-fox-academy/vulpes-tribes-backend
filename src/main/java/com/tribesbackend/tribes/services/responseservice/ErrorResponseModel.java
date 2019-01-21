package com.tribesbackend.tribes.services.responseservice;

public class ErrorResponseModel extends ResponseService {

    public ErrorResponseModel(String errorMessage) {
        super("error", errorMessage);
    }
}
