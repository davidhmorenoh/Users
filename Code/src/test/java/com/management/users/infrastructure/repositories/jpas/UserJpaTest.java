package com.management.users.infrastructure.repositories.jpas;

import com.management.users.domain.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserJpaTest {

    @Autowired
    private UserJpa userJpa;

    private UserEntity userEntity;
    private final Date currentDate = new Date();

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setName("Prueba");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
        userEntity.setActive(Boolean.TRUE);
        userEntity.setCreated(currentDate);
        userEntity.setModified(currentDate);
        userEntity.setLastLogin(currentDate);
        userEntity.setToken("token");

        userEntity = userJpa.save(userEntity);
    }

    @Test
    void testFindByEmail_UserExists() {
        Optional<UserEntity> foundUser = userJpa.findByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals(userEntity.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void testFindByEmail_UserDoesNotExist() {
        Optional<UserEntity> foundUser = userJpa.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testSaveUser() {
        UserEntity newUser = new UserEntity();
        newUser.setEmail("new@example.com");
        newUser.setPassword("newpassword");
        newUser.setName("Prueba");
        newUser.setActive(Boolean.TRUE);
        newUser.setCreated(currentDate);
        newUser.setModified(currentDate);
        newUser.setLastLogin(currentDate);
        newUser.setToken("token");

        UserEntity savedUser = userJpa.save(newUser);

        assertNotNull(savedUser);
        assertEquals(newUser.getEmail(), savedUser.getEmail());
        assertEquals(newUser.getPassword(), savedUser.getPassword());

        Optional<UserEntity> foundUser = userJpa.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(newUser, foundUser.get());
    }

    @Test
    void testFindById_UserExists() {
        Optional<UserEntity> foundUser = userJpa.findById(userEntity.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(userEntity, foundUser.get());
    }

    @Test
    void testFindById_UserDoesNotExist() {
        UUID nonExistentUserId = UUID.randomUUID();
        Optional<UserEntity> foundUser = userJpa.findById(nonExistentUserId);

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindAll() {
        List<UserEntity> users = userJpa.findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(userEntity.getEmail(), users.getFirst().getEmail());
        assertEquals(userEntity.getPassword(), users.getFirst().getPassword());
    }

    @Test
    void testDeleteUser() {
        userJpa.delete(userEntity);

        Optional<UserEntity> foundUser = userJpa.findById(userEntity.getId());
        assertFalse(foundUser.isPresent());
    }

}