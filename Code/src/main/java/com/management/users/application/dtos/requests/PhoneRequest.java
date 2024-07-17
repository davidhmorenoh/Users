package com.management.users.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private UUID id;

    @NotBlank(message = "Number phone is mandatory")
    @Size(min = 3, max = 10, message = "Number phone must be between 3 and 10 characters")
    private String number;

    @NotBlank(message = "City code phone is mandatory")
    @Size(max = 10, message = "City code phone must not exceed 10 characters")
    private String cityCode;

    @NotBlank(message = "Country code phone is mandatory")
    @Size(max = 10, message = "Country code phone must not exceed 10 characters")
    private String countryCode;

}