package com.management.users.application.services;

import com.management.users.application.dtos.requests.PhoneRequest;
import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.services.UserDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceTest {

    @Mock
    private UserDomainService userDomainService;

    @Mock
    private GenericMapper<UserRequest, UserResponse, UserEntity> userMapper;

    private UserEntity userEntity;
    private UserRequest userRequest, userUpdateRequest;
    private UserResponse userResponse;

    @InjectMocks
    private UserApplicationService userApplicationService;

    private final UUID userId = UUID.randomUUID();
    private final Date currentDate = new Date();

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Test User");
        userEntity.setEmail("test@user.com");
        userEntity.setPassword("Password1@");
        userEntity.setActive(Boolean.TRUE);

        userRequest = new UserRequest();
        userRequest.setName("Test User");
        userRequest.setEmail("test@user.com");
        userRequest.setPassword("Password1@");

        PhoneRequest phoneUpdateRequest = new PhoneRequest();
        phoneUpdateRequest.setId(userId);

        List<PhoneRequest> phoneUpdateRequests = new ArrayList<>();
        phoneUpdateRequests.add(phoneUpdateRequest);

        userUpdateRequest = new UserRequest();
        userUpdateRequest.setName("Updated User");
        userUpdateRequest.setEmail("updated@user.com");
        userUpdateRequest.setPassword("String1@");
        userUpdateRequest.setPhones(phoneUpdateRequests);

        userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setCreated(currentDate);
        userResponse.setModified(currentDate);
        userResponse.setLastLogin(currentDate);
    }

    @Test
    public void testGetAllUsers() {
        when(userDomainService.getAllUsers()).thenReturn(Collections.singletonList(userEntity));
        when(userDomainService.getAllUsers().stream().map(userMapper::toDto).collect(Collectors.toList())).thenReturn(Collections.singletonList(userResponse));

        List<UserResponse> users = userApplicationService.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        verify(userDomainService, times(2)).getAllUsers();
        verify(userMapper, times(users.size())).toDto(userEntity);
    }

    @Test
    public void testFindById() {
        when(userDomainService.findById(any(UUID.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponse);

        UserResponse foundUser = userApplicationService.findById(userId);
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userDomainService, times(1)).findById(userId);
        verify(userMapper, times(1)).toDto(any(UserEntity.class));
    }

    @Test
    public void testCreateUser() {
        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(userEntity);
        when(userDomainService.createUser(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponse);

        UserResponse createdUser = userApplicationService.createUser(userRequest);
        assertNotNull(createdUser);
        assertEquals(userId, createdUser.getId());
        verify(userMapper, times(1)).toEntity(any(UserRequest.class));
        verify(userDomainService, times(1)).createUser(any(UserEntity.class));
        verify(userMapper, times(1)).toDto(any(UserEntity.class));
    }

    @Test
    public void testUpdateUser() {
        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(userEntity);
        when(userDomainService.updateUser(any(UUID.class), any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponse);

        UserResponse updatedUser = userApplicationService.updateUser(userId, userUpdateRequest);
        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        verify(userMapper, times(1)).toEntity(any(UserRequest.class));
        verify(userDomainService, times(1)).updateUser(any(UUID.class), any(UserEntity.class));
        verify(userMapper, times(1)).toDto(any(UserEntity.class));
    }

    @Test
    public void testEnableUser() {
        when(userDomainService.enableUser(any(UUID.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponse);

        UserResponse enabledUser = userApplicationService.enableUser(userId);
        assertNotNull(enabledUser);
        assertEquals(userId, enabledUser.getId());
        verify(userDomainService, times(1)).enableUser(userId);
        verify(userMapper, times(1)).toDto(any(UserEntity.class));
    }

    @Test
    public void testDisableUser() {
        when(userDomainService.disableUser(any(UUID.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponse);

        UserResponse disabledUser = userApplicationService.disableUser(userId);
        assertNotNull(disabledUser);
        assertEquals(userId, disabledUser.getId());
        verify(userDomainService, times(1)).disableUser(userId);
        verify(userMapper, times(1)).toDto(any(UserEntity.class));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userDomainService).deleteUser(any(UUID.class));
        userApplicationService.deleteUser(userId);
        verify(userDomainService, times(1)).deleteUser(userId);
    }

}