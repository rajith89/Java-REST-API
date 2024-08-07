package com.testbranch.service;

import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MemberDto;

import java.util.List;

public interface MemberService {
    void addMemeber(MemberDto memberDto);

    List<MemberDto> getAllMembers();

    List<MemberDto> getAllMembersByGroup(Long groupId) throws ResourceNotFoundException;

    MemberDto updateMember(long id, MemberDto memberDto) throws ResourceNotFoundException;

    void deleteMember(long id) throws ResourceNotFoundException;
}
