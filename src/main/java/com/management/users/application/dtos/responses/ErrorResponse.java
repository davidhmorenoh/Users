package com.management.users.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private T message;

}