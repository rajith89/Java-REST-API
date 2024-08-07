package com.testbranch.service.impl;

import com.testbranch.model.Group;
import com.testbranch.model.Member;
import com.testbranch.repository.MemberRepository;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("memberService")
public class MemberServiceImpl implements com.testbranch.service.MemberService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void addMemeber(MemberDto memberDto) {
        Member member = new Member();
        BeanUtils.copyProperties(memberDto, member);
        Group group = new Group();
        group.setId(memberDto.getGroupId());
        member.setGroup(group);
        memberRepository.save(member);
    }


     @Override
     public List<MemberDto> getAllMembers(){
         List<Member> memeberList = memberRepository.findAll();
         List<MemberDto> memberDtoList = new ArrayList<>();
         if(!memeberList.isEmpty()){
             memberDtoList = memeberList.stream().map(member -> {
                 MemberDto memberDto = new MemberDto();
                 BeanUtils.copyProperties(member, memberDto);
                 if(member.getGroup() != null){
                     memberDto.setGroupId(member.getGroup().getId());
                     memberDto.setGroupName(member.getGroup().getName());
                 }
                 return memberDto;
             }).collect(Collectors.toList());
         }
         return memberDtoList;
     }

     @Override
     public List<MemberDto> getAllMembersByGroup(Long groupId) throws ResourceNotFoundException {
         Optional<List<Member>> memberBygroup = memberRepository.findAllByGroupId(groupId);
         List<MemberDto> memberDtoList = new ArrayList<>();
         if (!memberBygroup.isPresent()) {
             LOGGER.error("Members by group id :{} not found.", groupId);
             throw new ResourceNotFoundException("Members by group id :{} not found.");
         }else{
             memberDtoList = memberBygroup.get().stream().map(member -> {
                 MemberDto memberDto = new MemberDto();
                 BeanUtils.copyProperties(member, memberDto);
                 if(member.getGroup() != null){
                     memberDto.setGroupId(member.getGroup().getId());
                     memberDto.setGroupName(member.getGroup().getName());
                 }
                 return memberDto;
             }).collect(Collectors.toList());
         }
         return memberDtoList;
     }

     @Override
     public MemberDto updateMember(long id, MemberDto memberDto) throws ResourceNotFoundException {

        Member memberObj = new Member();
        MemberDto memberObjreturn = new MemberDto();
         Optional<Member> member = memberRepository.findById(id);
         if(member.isPresent()){
             BeanUtils.copyProperties(memberDto, memberObj);
             Group group =new Group();
             group.setId(memberDto.getGroupId());
             memberObj.setGroup(group);
             memberObj = memberRepository.save(memberObj);
             BeanUtils.copyProperties(memberObj, memberObjreturn);
         }else{
             LOGGER.error("Members id : {} not found.", id);
             throw new ResourceNotFoundException("Members id :{} not found.");
         }
         return memberObjreturn;
     }

    @Override
    public void deleteMember(long id) throws ResourceNotFoundException{
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Member not found.");
        }
    }

}
