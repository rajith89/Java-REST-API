package com.testbranch.service;

import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.CertificationDto;

import java.util.List;

public interface CertificationService {
    void addCertification(CertificationDto certificationDto);

    List<CertificationDto> getCertifications();

    CertificationDto updateCertification(long id, CertificationDto certificationDto) throws ResourceNotFoundException;

    void deleteCertification(long id) throws ResourceNotFoundException;
}
