package com.management.users.infrastructure.controllers;

import com.management.users.application.dtos.responses.ErrorResponse;
import com.management.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.management.users.domain.exceptions.UserAlreadyDisabledException;
import com.management.users.domain.exceptions.UserAlreadyEnabledException;
import com.management.users.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserAlreadyEnabledException.class,
            UserAlreadyDisabledException.class,
            EmailAlreadyRegisteredException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> handlePreconditionFailedException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.PRECONDITION_FAILED);
    }

}