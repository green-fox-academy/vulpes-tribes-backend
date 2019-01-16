package com.tribesbackend.tribes.tribesuser.service.errorservice;

import com.tribesbackend.tribes.tribesuser.service.ResponseService;

public class ErrorResponseModel extends ResponseService {

    public ErrorResponseModel(String errorMessage) {
        super("error", errorMessage);
    }
}
