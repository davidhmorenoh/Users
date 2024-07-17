package com.management.users.domain.services;

import com.management.users.domain.entities.PhoneEntity;
import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.management.users.domain.exceptions.RegularExpressionException;
import com.management.users.domain.exceptions.UserAlreadyDisabledException;
import com.management.users.domain.exceptions.UserAlreadyEnabledException;
import com.management.users.domain.repositories.UserRepository;
import com.management.users.infrastructure.configuration.JwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private UserDomainService userDomainService;

    @BeforeEach
    public void setUp() {
        userDomainService.setEmailRegularExpression("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        userDomainService.setPasswordRegularExpression("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");
    }

    @Test
    public void testGetAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setActive(true);
        userEntities.add(user);

        when(userRepository.findAll()).thenReturn(userEntities);

        userDomainService.getAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testCreateUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setPassword("NewPassword7@");
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(userId);
        phoneEntity.setNumber("64564");
        phoneEntityList.add(phoneEntity);
        user.setPhones(phoneEntityList);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$idFb2KIU4BuCKBDd7zESQetVK1qhxymNSjIcPUneedvry2aytPsF6");
        when(jwtConfig.generateToken(anyString())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwianRpIjoiYjQzNWJkNGEtZDZkZC00YTZlLTljYTgtMDBmYzM0NWIzZWFjIiwiaWF0IjoxNzIxMDg3MzczLCJleHAiOjE3MjEwOTA5NzN9.o_mta-GEqoNCpjmqV4nn7QdcIT7lkSu6iD47W2_GvZk");
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity createdUser = userDomainService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertTrue(createdUser.getActive());
        assertNotNull(createdUser.getToken());
        assertNotNull(createdUser.getCreated());
        assertNotNull(createdUser.getModified());
        assertNotNull(createdUser.getLastLogin());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(passwordEncoder, times(1)).encode("NewPassword7@");
        verify(jwtConfig, times(1)).generateToken(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("existing@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));

        UserEntity user = new UserEntity();
        user.setEmail(existingUser.getEmail());
        user.setPassword("Password1@");

        assertThrows(EmailAlreadyRegisteredException.class, () -> userDomainService.createUser(user));

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testCreateUser_EmailInvalid() {
        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("existing@m");

        UserEntity user = new UserEntity();
        user.setEmail(existingUser.getEmail());
        user.setPassword("Password1@");

        assertThrows(RegularExpressionException.class, () -> userDomainService.createUser(existingUser));

        verify(userRepository, never()).findByEmail(user.getEmail());
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testUpdateUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setName("Name");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("OldPassword8@");
        existingUser.setActive(Boolean.TRUE);
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(userId);
        phoneEntity.setNumber("64564");
        phoneEntityList.add(phoneEntity);
        existingUser.setPhones(phoneEntityList);

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setName("Name");
        updatedUser.setEmail("update@example.com");
        updatedUser.setPassword("NewPassword7@");
        updatedUser.setActive(Boolean.FALSE);
        updatedUser.setPhones(phoneEntityList);

        when(userRepository.findById(any(UUID.class))).thenReturn(existingUser);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$idFb2KIU4BuCKBDd7zESQetVK1qhxymNSjIcPUneedvry2aytPsF6");
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserEntity result = userDomainService.updateUser(userId, updatedUser);

        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(passwordEncoder.encode(updatedUser.getPassword()), result.getPassword());
        assertNotNull(result.getModified());

        verify(userRepository, times(1)).findById(userId);
        verify(passwordEncoder, times(2)).encode(updatedUser.getPassword());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testUpdateSameUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setName("Same name");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("$2a$10$SADfIpzci9pQ/oE91sUu..4PKqYav0Qu4uTJ0Uc77ScNRKBe8a2KC");
        existingUser.setActive(Boolean.FALSE);
        existingUser.setPhones(new ArrayList<>());

        UserEntity updatedUser = getUserEntity(userId);

        when(userRepository.findById(any(UUID.class))).thenReturn(existingUser);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$SADfIpzci9pQ/oE91sUu..4PKqYav0Qu4uTJ0Uc77ScNRKBe8a2KC");
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(Boolean.TRUE);
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserEntity result = userDomainService.updateUser(userId, updatedUser);

        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(passwordEncoder.encode(updatedUser.getPassword()), result.getPassword());
        assertNotNull(result.getModified());

        verify(userRepository, times(1)).findById(userId);
        verify(passwordEncoder, times(1)).encode(updatedUser.getPassword());
        verify(userRepository, times(1)).save(existingUser);
    }

    private static UserEntity getUserEntity(UUID userId) {
        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setName("Same name");
        updatedUser.setEmail("existing@example.com");
        updatedUser.setPassword("OldPassword8@");
        updatedUser.setActive(Boolean.FALSE);
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(userId);
        phoneEntity.setNumber("64564");
        phoneEntityList.add(phoneEntity);
        updatedUser.setPhones(phoneEntityList);
        return updatedUser;
    }

    @Test
    public void testUpdateSameUserActiveIsNull_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("$2a$10$idFb2KIU4BuCKBDd7zESQetVK1qhxymNSjIcPUneedvry2aytPsF6");
        existingUser.setActive(Boolean.FALSE);
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(userId);
        phoneEntity.setNumber("64564");
        phoneEntityList.add(phoneEntity);
        existingUser.setPhones(phoneEntityList);

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setEmail("existing@example.com");
        updatedUser.setPassword("OldPassword8@");
        updatedUser.setActive(null);
        updatedUser.setPhones(phoneEntityList);

        when(userRepository.findById(any(UUID.class))).thenReturn(existingUser);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$idFb2KIU4BuCKBDd7zESQetVK1qhxymNSjIcPUneedvry2aytPsF6");
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserEntity result = userDomainService.updateUser(userId, updatedUser);

        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(passwordEncoder.encode(updatedUser.getPassword()), result.getPassword());
        assertNotNull(result.getModified());

        verify(userRepository, times(1)).findById(userId);
        verify(passwordEncoder, times(2)).encode(updatedUser.getPassword());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testUpdateUser_EmailAlreadyExists() {
        UUID userId = UUID.randomUUID();

        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(userId);
        phoneEntity.setNumber("64564");
        phoneEntityList.add(phoneEntity);

        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setPassword("NewPassword7@");
        existingUser.setEmail("existing@example.com");
        existingUser.setPhones(phoneEntityList);

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setPassword("NewPassword7@");
        updatedUser.setName("Other name");
        updatedUser.setEmail("existing1@example.com");
        updatedUser.setPhones(phoneEntityList);

        when(userRepository.findById(any(UUID.class))).thenReturn(existingUser);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));

        assertThrows(EmailAlreadyRegisteredException.class,
                () -> userDomainService.updateUser(userId, updatedUser));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).findByEmail(updatedUser.getEmail());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testEnableUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setActive(false);

        when(userRepository.findById(any(UUID.class))).thenReturn(user);
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userDomainService.enableUser(userId);

        assertTrue(result.getActive());
        assertNotNull(result.getModified());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testEnableUser_AlreadyEnabled() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setActive(true);

        when(userRepository.findById(any(UUID.class))).thenReturn(user);

        assertThrows(UserAlreadyEnabledException.class, () -> userDomainService.enableUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testDisableUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setActive(true);

        when(userRepository.findById(any(UUID.class))).thenReturn(user);
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userDomainService.disableUser(userId);

        assertFalse(result.getActive());
        assertNotNull(result.getModified());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDisableUser_AlreadyDisabled() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setActive(false);

        when(userRepository.findById(any(UUID.class))).thenReturn(user);

        assertThrows(UserAlreadyDisabledException.class, () -> userDomainService.disableUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testDeleteUser_Success() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("test@example.com");

        when(userRepository.findById(any(UUID.class))).thenReturn(user);

        userDomainService.deleteUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }

}