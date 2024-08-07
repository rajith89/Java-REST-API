package com.testbranch.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDto {

    private long id;
    private String name;
    private String code;
    private String issuer;
    private String design;
    private String description;
    private boolean published;
    private Date startDate;
    private Date expiaryDate;
    private Set<GroupDto> groups;

}
