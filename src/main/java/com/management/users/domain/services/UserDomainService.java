package com.management.users.domain.services;

import com.management.users.domain.entities.PhoneEntity;
import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.management.users.domain.exceptions.RegularExpressionException;
import com.management.users.domain.exceptions.UserAlreadyDisabledException;
import com.management.users.domain.exceptions.UserAlreadyEnabledException;
import com.management.users.domain.repositories.UserRepository;
import com.management.users.infrastructure.configuration.JwtConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserDomainService {

    private final String EXCEPTION_MESSAGE_EMAIL_REGULAR_EXPRESSION = "User email must be valid under the email configurable format";
    private final String EXCEPTION_MESSAGE_PASSWORD_REGULAR_EXPRESSION = "User password must be valid under the password configurable format";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Value("${regex.email.regexp}")
    private String emailRegularExpression;
    @Value("${regex.password.regexp}")
    private String passwordRegularExpression;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        validateRegularExpression(user.getEmail(), getEmailRegularExpression(), EXCEPTION_MESSAGE_EMAIL_REGULAR_EXPRESSION);
        validateRegularExpression(user.getPassword(), getPasswordRegularExpression(), EXCEPTION_MESSAGE_PASSWORD_REGULAR_EXPRESSION);
        findByEmail(user.getEmail());
        Date currentDate = new Date();

        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtConfig.generateToken(user.getEmail()));
        user.setCreated(currentDate);
        user.setModified(currentDate);
        user.setLastLogin(currentDate);
        user.setActive(Boolean.TRUE);

        return userRepository.save(user);
    }

    public UserEntity updateUser(UUID id, UserEntity userToUpdate) {
        UserEntity currentUser = findById(id);
        validateRegularExpression(userToUpdate.getEmail(), getEmailRegularExpression(), EXCEPTION_MESSAGE_EMAIL_REGULAR_EXPRESSION);
        validateRegularExpression(userToUpdate.getPassword(), getPasswordRegularExpression(), EXCEPTION_MESSAGE_PASSWORD_REGULAR_EXPRESSION);

        currentUser.setModified(new Date());
        if (validateIfValuesAreDifferent(currentUser.getName(), userToUpdate.getName())) {
            currentUser.setName(userToUpdate.getName());
        }
        if (validateIfValuesAreDifferent(currentUser.getEmail(), userToUpdate.getEmail())) {
            findByEmail(userToUpdate.getEmail());
            currentUser.setEmail(userToUpdate.getEmail());
        }
        if (!passwordEncoder.matches(userToUpdate.getPassword(), currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        }
        if (Objects.nonNull(userToUpdate.getActive()) && validateIfValuesAreDifferent(currentUser.getActive(), userToUpdate.getActive())) {
            currentUser.setActive(userToUpdate.getActive());
        }
        updatePhones(currentUser.getPhones(), userToUpdate.getPhones());

        return userRepository.save(currentUser);
    }

    public UserEntity enableUser(UUID id) {
        UserEntity user = findById(id);

        if (user.getActive()) {
            throw new UserAlreadyEnabledException("The user is already enabled.");
        }
        user.setActive(Boolean.TRUE);
        user.setModified(new Date());

        return userRepository.save(user);
    }

    public UserEntity disableUser(UUID id) {
        UserEntity user = findById(id);

        if (!user.getActive()) {
            throw new UserAlreadyDisabledException("The user is already disabled.");
        }
        user.setActive(Boolean.FALSE);
        user.setModified(new Date());

        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        UserEntity user = findById(id);
        userRepository.delete(user);
    }

    private void findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyRegisteredException("Email is already in use.");
        }
    }

    private void validateRegularExpression(String value, String regularExpression, String exceptionMessage) {
        if (!value.matches(regularExpression)) {
            throw new RegularExpressionException(exceptionMessage);
        }
    }

    private <T> boolean validateIfValuesAreDifferent(T currentValue, T newValue) {
        if (Objects.isNull(currentValue) && Objects.isNull(newValue)) {
            return false;
        } else if (Objects.isNull(currentValue) || Objects.isNull(newValue)) {
            return true;
        }
        return !currentValue.equals(newValue);
    }

    private void updatePhones(List<PhoneEntity> currentPhones, List<PhoneEntity> newPhones) {
        Set<UUID> currentPhoneIds = currentPhones.stream()
                .map(PhoneEntity::getId)
                .collect(Collectors.toSet());

        for (PhoneEntity newPhone : newPhones) {
            if (!currentPhoneIds.contains(newPhone.getId())) {
                currentPhones.add(newPhone);
            } else {
                currentPhones.stream()
                        .filter(currentPhone -> currentPhone.getId().equals(newPhone.getId()))
                        .findFirst()
                        .ifPresent(currentPhone -> {
                            currentPhone.setNumber(newPhone.getNumber());
                            currentPhone.setCityCode(newPhone.getCityCode());
                            currentPhone.setCountryCode(newPhone.getCountryCode());
                        });
            }
        }
    }

}