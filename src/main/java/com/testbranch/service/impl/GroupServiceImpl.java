package com.testbranch.service.impl;

import com.testbranch.model.Certification;
import com.testbranch.model.Group;
import com.testbranch.model.Member;
import com.testbranch.repository.GroupRepository;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("groupService")
public class GroupServiceImpl implements com.testbranch.service.GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public GroupDto addGroup(GroupDto groupDto) throws ResourceAlreadyExistsException {
        GroupDto groupDtoreturn = new GroupDto();
        if (!isExisting(groupDto)) {
            Group group = new Group();
            BeanUtils.copyProperties(groupDto, group);
            Certification certification = new Certification();
            certification.setId(groupDto.getCertification().getId());
            group.setCertification(certification);
            group = groupRepository.save(group);
            BeanUtils.copyProperties(group, groupDtoreturn);
        }
        return groupDtoreturn;
    }

    @Override
    public List<GroupDto> getGroups() {

        List<Group> groupList =  groupRepository.findAll();
        List<GroupDto> groupDtoList = new ArrayList<>();
        if(!groupList.isEmpty()) {
            groupDtoList = groupList.stream().map(p -> {
                GroupDto groupDtoRetrun = new GroupDto();
                BeanUtils.copyProperties(p, groupDtoRetrun);
                return groupDtoRetrun;
            }).collect(Collectors.toList());
        }
        return groupDtoList;
    }


    @Override
    public GroupDto updateGroup(long id, GroupDto groupDto) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        GroupDto groupDtoReturn = new GroupDto();
        Optional<Group> group = groupRepository.findById(id);
        if (!group.isPresent()) {
            throw new ResourceNotFoundException("Group not found.");
        }else{
            Optional<Group> existingGroupByName = groupRepository.findByName(groupDto.getName());
            if(existingGroupByName.isPresent() && existingGroupByName.get().getId() != id) {
                throw new ResourceAlreadyExistsException("Group Name Already Existing");
            }
            Group groupEdit = new Group();
            BeanUtils.copyProperties(groupDto, groupEdit);

            Set<Member> memberList = new HashSet<>();
            if(groupDto.getMembers() !=null && groupDto.getMembers().size()>0){
                groupDto.getMembers().forEach(memberDto -> {
                    Member member= new Member();
                    BeanUtils.copyProperties(memberDto,member);
                    memberList.add(member);
                });
            }
            if(memberList.size()>0){
                groupEdit.setMembers(memberList);
            }
            groupEdit = groupRepository.save(groupEdit);
            BeanUtils.copyProperties(groupEdit, groupDtoReturn);
        }
        return groupDtoReturn;
    }

    @Override
    public void deleteGroup(long id) throws ResourceNotFoundException{
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            groupRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Group not found.");
        }
    }


    private boolean isExisting(GroupDto groupDto) throws ResourceAlreadyExistsException {
        Optional<Group> groupOptional = groupRepository.findByName(groupDto.getName());
        if (groupOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Group Name Already Existing.");
        }
        return false;
    }

}
