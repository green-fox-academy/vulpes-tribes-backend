package com.tribes_backend.tribes.tribesUser.controller;

import com.tribes_backend.tribes.tribesUser.exception.InvalidUserPasswordException;
import com.tribes_backend.tribes.tribesUser.exception.TribesError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidUserPasswordException.class, InvalidUserPasswordException.class})
    protected ResponseEntity<Object> handleWrongPassword( InvalidUserPasswordException ex) {
        return new ResponseEntity<Object>(new TribesError(ex.getErrortype(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }


}


