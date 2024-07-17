package com.management.users.infrastructure.repositories.implementations;

import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.exceptions.UserNotFoundException;
import com.management.users.infrastructure.repositories.jpas.UserJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImplUserRepositoryTest {

    @Mock
    private UserJpa userJpa;

    @InjectMocks
    private ImplUserRepository userRepository;

    private UserEntity userEntity;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
    }

    @Test
    void testFindByEmail_UserExists() {
        when(userJpa.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> foundUser = userRepository.findByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals(userEntity, foundUser.get());
        verify(userJpa, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindByEmail_UserDoesNotExist() {
        when(userJpa.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<UserEntity> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
        verify(userJpa, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testSaveUser() {
        when(userJpa.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);

        assertNotNull(savedUser);
        assertEquals(userEntity, savedUser);
        verify(userJpa, times(1)).save(userEntity);
    }

    @Test
    void testFindById_UserExists() {
        when(userJpa.findById(any(UUID.class))).thenReturn(Optional.of(userEntity));

        UserEntity foundUser = userRepository.findById(userId);

        assertNotNull(foundUser);
        assertEquals(userEntity, foundUser);
        verify(userJpa, times(1)).findById(userId);
    }

    @Test
    void testFindById_UserDoesNotExist() {
        when(userJpa.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userRepository.findById(userId));
        verify(userJpa, times(1)).findById(userId);
    }

    @Test
    void testFindAll() {
        List<UserEntity> users = List.of(userEntity);
        when(userJpa.findAll()).thenReturn(users);

        List<UserEntity> foundUsers = userRepository.findAll();

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(userEntity, foundUsers.getFirst());
        verify(userJpa, times(1)).findAll();
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userJpa).delete(any(UserEntity.class));

        userRepository.delete(userEntity);

        verify(userJpa, times(1)).delete(userEntity);
    }

}