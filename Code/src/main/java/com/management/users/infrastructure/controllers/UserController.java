package com.management.users.infrastructure.controllers;

import com.management.users.application.dtos.requests.creation.UserCreationRequest;
import com.management.users.application.dtos.requests.update.UserUpdateRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.use_cases.users.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GetAllUseCase getAllUseCase;
    private final GetByIdUseCase getByIdUseCase;
    private final CreateUseCase createUseCase;
    private final UpdateUseCase updateUseCase;
    private final EnableUseCase enableUseCase;
    private final DisableUseCase disableUseCase;
    private final DeleteUseCase deleteUseCase;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(getAllUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreationRequest userRequest) {
        return ResponseEntity.ok(createUseCase.execute(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest userRequest) {
        return ResponseEntity.ok(updateUseCase.execute(id, userRequest));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<UserResponse> enableUser(@PathVariable UUID id) {
        return ResponseEntity.ok(enableUseCase.execute(id));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserResponse> disableUser(@PathVariable UUID id) {
        return ResponseEntity.ok(disableUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}