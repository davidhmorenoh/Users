package com.management.users.application.use_cases.users;

import com.management.users.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUseCase {

    private final UserApplicationService userApplicationService;

    public void execute(UUID id) {
        userApplicationService.deleteUser(id);
    }

}