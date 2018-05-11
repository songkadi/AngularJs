package com.songkadi.exception.adviser;

import com.songkadi.exception.UserNotFoundException;
import com.songkadi.exception.UserDuplicatedException;
import com.songkadi.util.CustomErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviser {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> notfoundException(UserNotFoundException e) {
        return new ResponseEntity(new CustomErrorType(e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity<?> duplicatedException(UserDuplicatedException e) {
        return new ResponseEntity(new CustomErrorType(e.getMessage()), e.getHttpStatus());
    }
}
