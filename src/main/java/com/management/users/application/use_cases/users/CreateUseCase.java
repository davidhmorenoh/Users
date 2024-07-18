package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUseCase {

    private final UserApplicationService userApplicationService;

    public UserResponse execute(UserRequest userRequest) {
        return userApplicationService.createUser(userRequest);
    }

}