package com.management.users.domain.services;

import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.management.users.domain.exceptions.UserAlreadyDisabledException;
import com.management.users.domain.exceptions.UserAlreadyEnabledException;
import com.management.users.domain.repositories.UserRepository;
import com.management.users.infrastructure.configuration.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id);
    }

    private void findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyRegisteredException("Email is already in use.");
        }
    }

    public UserEntity createUser(UserEntity user) {
        findByEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtConfig.generateToken(user.getEmail()));
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    public UserEntity updateUser(UUID id, UserEntity userToUpdate) {
        UserEntity currentUser = findById(id);
        if (!userToUpdate.getEmail().equalsIgnoreCase(currentUser.getEmail())) {
            findByEmail(userToUpdate.getEmail());
        }
        currentUser.setActive(userToUpdate.isActive());
        currentUser.setCreated(userToUpdate.getCreated());
        currentUser.setEmail(userToUpdate.getEmail());
        currentUser.setName(userToUpdate.getName());
        currentUser.setModified(userToUpdate.getModified());
        currentUser.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        currentUser.setToken(userToUpdate.getToken());
        currentUser.setLastLogin(userToUpdate.getLastLogin());
        currentUser.setPhones(userToUpdate.getPhones());
        return userRepository.save(currentUser);
    }

    public UserEntity enableUser(UUID id) {
        UserEntity user = findById(id);
        if (user.isActive()) {
            throw new UserAlreadyEnabledException("The user is already enabled.");
        }
        user.setActive(Boolean.FALSE);
        return userRepository.save(user);
    }

    public UserEntity disableUser(UUID id) {
        UserEntity user = findById(id);
        if (!user.isActive()) {
            throw new UserAlreadyDisabledException("The user is already disabled.");
        }
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        UserEntity user = findById(id);
        userRepository.delete(user);
    }

}