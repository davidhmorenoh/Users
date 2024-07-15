package com.management.users.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private String id;

    private Date created;

    private Date modified;

    private Date lastLogin;

    private String token;

    private boolean active;

    private List<PhoneResponse> phones;

}