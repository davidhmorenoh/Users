package com.management.users.infrastructure.controllers;

import com.management.users.application.dtos.responses.ErrorResponse;
import com.management.users.domain.exceptions.*;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse<String>> handleNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserAlreadyEnabledException.class,
            UserAlreadyDisabledException.class,
            EmailAlreadyRegisteredException.class,
            HttpMessageNotReadableException.class,
            PropertyValueException.class,
            RegularExpressionException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse<String>> handlePreconditionFailedException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ErrorResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

}