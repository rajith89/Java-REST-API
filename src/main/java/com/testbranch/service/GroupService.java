package com.testbranch.service;

import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;

import java.util.List;

public interface GroupService {
    GroupDto addGroup(GroupDto groupDto) throws ResourceAlreadyExistsException;

    List<GroupDto> getGroups();

    GroupDto updateGroup(long id, GroupDto groupDto) throws ResourceAlreadyExistsException, ResourceNotFoundException;

    void deleteGroup(long id) throws ResourceNotFoundException;
}
