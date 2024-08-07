package com.testbranch.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbranch.service.GroupService;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;
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
public class GroupResourceTest {

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupResource groupResource;

    private GroupDto groupDto;

    @BeforeEach
    public void setUp() {
        groupDto = new GroupDto();
        // Set up your GroupDto object here if needed
    }

    @Test
    public void testCreateGroup() throws ResourceAlreadyExistsException {
        ResponseEntity<Void> responseEntity = groupResource.createGroup(groupDto);
        verify(groupService, times(1)).addGroup(any(GroupDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllGroups() {
        List<GroupDto> groups = new ArrayList<>();
        when(groupService.getGroups()).thenReturn(groups);

        ResponseEntity<List<GroupDto>> responseEntity = groupResource.getAllGroups();
        verify(groupService, times(1)).getGroups();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groups, responseEntity.getBody());
    }

    @Test
    public void testUpdateGroup() throws ResourceNotFoundException, ResourceAlreadyExistsException {
        GroupDto updatedGroup = new GroupDto();
        when(groupService.updateGroup(anyLong(), any(GroupDto.class))).thenReturn(updatedGroup);

        ResponseEntity<GroupDto> responseEntity = groupResource.updateGroup(groupDto, 1L);
        verify(groupService, times(1)).updateGroup(1L, groupDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedGroup, responseEntity.getBody());
    }

    @Test
    public void testDeleteGroup() throws ResourceNotFoundException {
        ResponseEntity<Void> responseEntity = groupResource.deleteGroup(1L);
        verify(groupService, times(1)).deleteGroup(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
