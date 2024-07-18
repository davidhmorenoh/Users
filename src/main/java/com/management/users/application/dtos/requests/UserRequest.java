package com.management.users.application.dtos.requests;

import com.management.users.application.validation.ValidEmailFormat;
import com.management.users.application.validation.ValidPasswordFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "User email is mandatory")
    @ValidEmailFormat
    private String email;

    @NotBlank(message = "User password is mandatory")
    @ValidPasswordFormat
    private String password;

    @NotNull(message = "User phones is mandatory")
    private List<PhoneRequest> phones;

}