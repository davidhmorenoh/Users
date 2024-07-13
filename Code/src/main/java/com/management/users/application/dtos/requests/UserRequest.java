package com.management.users.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "User email is mandatory")
    @Email(message = "User email should be valid")
    private String email;

    @NotBlank(message = "User password is mandatory")
    @Size(min = 6, max = 100, message = "User password must be between 6 and 100 characters")
    private String password;

    @NotNull(message = "User phones is mandatory")
    private List<PhoneRequest> phones;

}