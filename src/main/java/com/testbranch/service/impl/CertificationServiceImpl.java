package com.testbranch.service.impl;

import com.testbranch.model.Certification;
import com.testbranch.model.Group;
import com.testbranch.repository.CertificationRepository;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.CertificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("certificationService")
public class CertificationServiceImpl implements com.testbranch.service.CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void addCertification(CertificationDto certificationDto){
        Certification certification = new Certification();
        BeanUtils.copyProperties(certificationDto, certification);

        Set<Group> groupList = new HashSet<>();
        if(certificationDto.getGroups() !=null && certificationDto.getGroups().size()>0){
            certificationDto.getGroups().forEach(groupDto -> {
                Group group= new Group();
                BeanUtils.copyProperties(groupDto,group);
                groupList.add(group);
            });
        }
        if(groupList.size()>0){
            certification.setGroups(groupList);
        }
        certificationRepository.save(certification);
    }

    @Override
    public List<CertificationDto> getCertifications() {
        List<Certification> certificationList =  certificationRepository.findAll();
        List<CertificationDto> certificationDtoList = new ArrayList<>();
        if(!certificationList.isEmpty()) {
            certificationDtoList = certificationList.stream().map(p -> {
                CertificationDto certificationDto = new CertificationDto();
                BeanUtils.copyProperties(p, certificationDto);
                return certificationDto;
            }).collect(Collectors.toList());
        }
        return certificationDtoList;
    }


    @Override
    public CertificationDto updateCertification(long id, CertificationDto certificationDto) throws ResourceNotFoundException {
        CertificationDto certificationReturn = new CertificationDto();
        Optional<Certification> certification = certificationRepository.findById(id);
        if (!certification.isPresent()) {
            throw new ResourceNotFoundException("certification not found.");
        }else{
            Certification certificationObj = new Certification();
            BeanUtils.copyProperties(certificationDto, certificationObj);

            Set<Group> groupList = new HashSet<>();
            if(certificationDto.getGroups() !=null && certificationDto.getGroups() .size()>0){
                certificationDto.getGroups() .forEach(groupDto -> {
                    Group group= new Group();
                    BeanUtils.copyProperties(groupDto,group);
                    groupList.add(group);
                });
            }
            if(groupList.size()>0){
                certificationObj.setGroups(groupList);
            }
            certificationObj = certificationRepository.save(certificationObj);
            BeanUtils.copyProperties(certificationObj, certificationReturn);
        }
        return certificationReturn;
    }

    @Override
    public void deleteCertification(long id) throws ResourceNotFoundException{
        Optional<Certification> certificationOptional = certificationRepository.findById(id);
        if (certificationOptional.isPresent()) {
            certificationRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("certification not found.");
        }
    }

}
