package com.management.users.infrastructure.repositories.implementations;

import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.exceptions.UserNotFoundException;
import com.management.users.domain.repositories.UserRepository;
import com.management.users.infrastructure.repositories.jpas.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ImplUserRepository implements UserRepository {

    private final UserJpa userJpa;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpa.findByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userJpa.save(user);
    }

    @Override
    public UserEntity findById(UUID id) {
        return userJpa.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserEntity> findAll() {
        return userJpa.findAll();
    }

    @Override
    public void delete(UserEntity user) {
        userJpa.delete(user);
    }

}