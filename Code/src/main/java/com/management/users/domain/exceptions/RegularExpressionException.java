package com.management.users.domain.exceptions;

public class RegularExpressionException extends IllegalStateException {

    public RegularExpressionException(String message) {
        super(message);
    }

}