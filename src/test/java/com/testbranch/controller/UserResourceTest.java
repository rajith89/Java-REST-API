package com.testbranch.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbranch.service.UserService;
import com.testbranch.web.error.UserAlreadyExistException;
import com.testbranch.web.error.UserNotFoundException;
import com.testbranch.web.rest.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        // Set up your UserDto object here if needed
    }

    @Test
    public void testRegisterUser() throws UserAlreadyExistException {
        ResponseEntity<Void> responseEntity = userResource.registerUser(userDto);
        verify(userService, times(1)).createUserAccount(any(UserDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> users = new ArrayList<>();
        when(userService.getAllActiveUsers(1)).thenReturn(users);

        ResponseEntity<List<UserDto>> responseEntity = userResource.getAllUsers();
        verify(userService, times(1)).getAllActiveUsers(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException {
        UserDto updatedUser = new UserDto();
        when(userService.updateUser(anyLong(), any(UserDto.class))).thenReturn(updatedUser);

        ResponseEntity<UserDto> responseEntity = userResource.updateUser(userDto, 1L);
        verify(userService, times(1)).updateUser(1L, userDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        ResponseEntity<Void> responseEntity = userResource.deleteUser(1L);
        verify(userService, times(1)).deleteUser(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

