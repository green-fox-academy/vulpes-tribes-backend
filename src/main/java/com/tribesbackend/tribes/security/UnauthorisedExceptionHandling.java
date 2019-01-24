package com.tribesbackend.tribes.security;

import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UnauthorisedExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity exception (){
        return new ResponseEntity(new ErrorResponseModel("No token"), HttpStatus.FORBIDDEN);
    }
}
