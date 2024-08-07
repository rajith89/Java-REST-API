package com.testbranch.controller;

import com.testbranch.service.UserService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.error.UserAlreadyExistException;
import com.testbranch.web.error.UserNotFoundException;
import com.testbranch.web.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDto accountDto) throws UserAlreadyExistException {
        userService.createUserAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllActiveUsers(1));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable long id)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(id,userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id)
            throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
