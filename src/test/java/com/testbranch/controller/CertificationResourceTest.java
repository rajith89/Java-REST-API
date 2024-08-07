package com.testbranch.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbranch.service.CertificationService;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.CertificationDto;
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
public class CertificationResourceTest {

    @Mock
    private CertificationService certificationService;

    @InjectMocks
    private CertificationResource certificationResource;

    private CertificationDto certificationDto;

    @BeforeEach
    public void setUp() {
        certificationDto = new CertificationDto();
        // Set up your CertificationDto object here if needed
    }

    @Test
    public void testCreateCertification() throws ResourceAlreadyExistsException {
        ResponseEntity<Void> responseEntity = certificationResource.createCertification(certificationDto);
        verify(certificationService, times(1)).addCertification(any(CertificationDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCertifications() {
        List<CertificationDto> certifications = new ArrayList<>();
        when(certificationService.getCertifications()).thenReturn(certifications);

        ResponseEntity<List<CertificationDto>> responseEntity = certificationResource.getCertifications();
        verify(certificationService, times(1)).getCertifications();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(certifications, responseEntity.getBody());
    }

    @Test
    public void testUpdateCertification() throws ResourceNotFoundException {
        CertificationDto updatedCertification = new CertificationDto();
        when(certificationService.updateCertification(anyLong(), any(CertificationDto.class))).thenReturn(updatedCertification);

        ResponseEntity<CertificationDto> responseEntity = certificationResource.updateGroup(certificationDto, 1L);
        verify(certificationService, times(1)).updateCertification(1L, certificationDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedCertification, responseEntity.getBody());
    }

    @Test
    public void testDeleteCertification() throws ResourceNotFoundException {
        ResponseEntity<Void> responseEntity = certificationResource.deleteCertification(1L);
        verify(certificationService, times(1)).deleteCertification(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
