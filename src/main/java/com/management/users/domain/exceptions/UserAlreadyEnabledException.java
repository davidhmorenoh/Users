package com.management.users.domain.exceptions;

public class UserAlreadyEnabledException extends IllegalStateException {

    public UserAlreadyEnabledException(String message) {
        super(message);
    }

}