package com.websystique.springboot.exception.controller;

import com.websystique.springboot.exception.UserDuplicatedException;
import com.websystique.springboot.exception.UserNotFoundException;
import com.websystique.springboot.util.CustomErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> notfoundException(UserNotFoundException e) {
        return new ResponseEntity(new CustomErrorType(e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity<?> duplicatedException(UserDuplicatedException e) {
        return new ResponseEntity(new CustomErrorType(e.getMessage()), e.getHttpStatus());
    }
}
