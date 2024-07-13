package com.management.users.infrastructure.repositories.jpas;

import com.management.users.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpa extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

}