package com.websystique.springboot.exception;

import com.websystique.springboot.model.User;
import org.springframework.http.HttpStatus;

public class UserDuplicatedException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public UserDuplicatedException(User user) {
        super("A User with name " + user.getName() + " already exist");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
