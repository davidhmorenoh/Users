package com.management.users.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse<T> implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private T message;

}