package com.management.users.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private UUID id;

    private String number;

    private String cityCode;

    private String countryCode;

}