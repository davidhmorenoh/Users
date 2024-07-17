package com.management.users.domain.repositories;

import com.management.users.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserEntity> findByEmail(String email);

    UserEntity save(UserEntity user);

    UserEntity findById(UUID id);

    List<UserEntity> findAll();

    void delete(UserEntity user);

}