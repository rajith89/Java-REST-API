package com.testbranch.controller;

import com.testbranch.service.CertificationService;
import com.testbranch.web.error.ResourceAlreadyExistsException;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.CertificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cetification")
public class CertificationResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CertificationService certificationService;

    @PostMapping("/")
    public ResponseEntity<Void> createCertification(@Valid @RequestBody CertificationDto certificationDto) throws ResourceAlreadyExistsException {
        certificationService.addCertification(certificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CertificationDto>> getCertifications() {
        return ResponseEntity.ok(certificationService.getCertifications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationDto> updateGroup(@Valid @RequestBody CertificationDto certificationDto, @PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(certificationService.updateCertification(id,certificationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable long id) throws ResourceNotFoundException {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }

}
