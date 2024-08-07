package com.testbranch.controller;

import com.testbranch.service.MemberService;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;
import com.testbranch.web.rest.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberDto memberDto) {
        memberService.addMemeber(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<MemberDto>> getAllMembersByGroup(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(memberService.getAllMembersByGroup(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@Valid @RequestBody MemberDto memberDto, @PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(memberService.updateMember(id,memberDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable long id)
            throws ResourceNotFoundException {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
