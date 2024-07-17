package com.management.users.domain.exceptions;

public class UserAlreadyDisabledException extends IllegalStateException {

    public UserAlreadyDisabledException(String message) {
        super(message);
    }

}