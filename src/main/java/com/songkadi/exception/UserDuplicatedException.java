package com.songkadi.exception;

import com.songkadi.model.AppUser;
import org.springframework.http.HttpStatus;

public class UserDuplicatedException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public UserDuplicatedException(AppUser appUser) {
        super("A AppUser with name " + appUser.getName() + " already exist");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
