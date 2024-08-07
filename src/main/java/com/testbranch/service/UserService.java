package com.testbranch.service;

import com.testbranch.model.User;
import com.testbranch.web.error.UserAlreadyExistException;
import com.testbranch.web.error.UserNotFoundException;
import com.testbranch.web.rest.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException;

    void deleteUser(Long id) throws UserNotFoundException;

    User findUserByEmail(String email);

    UserDto findUserById(long id) throws UserNotFoundException;

    List<UserDto> getAllActiveUsers(int status);
}
