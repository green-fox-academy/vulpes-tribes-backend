package com.tribesbackend.tribes.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UnauthorisedExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler({JWTDecodeException.class})
    public ResponseEntity exception (){
        return new ResponseEntity(new ErrorResponseModel("No (Invalid token"), HttpStatus.FORBIDDEN);
    }
}
//AccessDeniedException.class