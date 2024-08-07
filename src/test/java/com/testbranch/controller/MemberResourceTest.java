package com.testbranch.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbranch.service.MemberService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MemberDto;
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
public class MemberResourceTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberResource memberResource;

    private MemberDto memberDto;

    @BeforeEach
    public void setUp() {
        memberDto = new MemberDto();
        // Set up your MemberDto object here if needed
    }

    @Test
    public void testCreateMember() {
        ResponseEntity<Void> responseEntity = memberResource.createMember(memberDto);
        verify(memberService, times(1)).addMemeber(any(MemberDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllMembers() {
        List<MemberDto> members = new ArrayList<>();
        when(memberService.getAllMembers()).thenReturn(members);

        ResponseEntity<List<MemberDto>> responseEntity = memberResource.getAllMembers();
        verify(memberService, times(1)).getAllMembers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(members, responseEntity.getBody());
    }

    @Test
    public void testGetAllMembersByGroup() throws ResourceNotFoundException {
        List<MemberDto> members = new ArrayList<>();
        when(memberService.getAllMembersByGroup(anyLong())).thenReturn(members);

        ResponseEntity<List<MemberDto>> responseEntity = memberResource.getAllMembersByGroup(1L);
        verify(memberService, times(1)).getAllMembersByGroup(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(members, responseEntity.getBody());
    }

    @Test
    public void testUpdateMember() throws ResourceNotFoundException {
        MemberDto updatedMember = new MemberDto();
        when(memberService.updateMember(anyLong(), any(MemberDto.class))).thenReturn(updatedMember);

        ResponseEntity<MemberDto> responseEntity = memberResource.updateMember(memberDto, 1L);
        verify(memberService, times(1)).updateMember(1L, memberDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedMember, responseEntity.getBody());
    }

    @Test
    public void testDeleteMember() throws ResourceNotFoundException {
        ResponseEntity<Void> responseEntity = memberResource.deleteMember(1L);
        verify(memberService, times(1)).deleteMember(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

