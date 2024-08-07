package com.testbranch.controller;

import com.testbranch.service.GroupService;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.error.UserAlreadyExistException;
import com.testbranch.web.error.UserNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;
import com.testbranch.web.rest.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private GroupService groupService;

    @PostMapping("/")
    public ResponseEntity<Void> createGroup(@Valid @RequestBody GroupDto groupDto) throws  ResourceAlreadyExistsException {
        groupService.addGroup(groupDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getGroups());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@Valid @RequestBody GroupDto groupDto, @PathVariable long id)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return ResponseEntity.ok(groupService.updateGroup(id,groupDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable long id)
            throws ResourceNotFoundException {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

}
