package com.songkadi.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UserNotFoundException(long id) {
        super("AppUser with id " + id + " not found");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}