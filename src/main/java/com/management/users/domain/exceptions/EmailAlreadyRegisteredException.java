package com.management.users.domain.exceptions;

public class EmailAlreadyRegisteredException extends IllegalStateException {

    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

}