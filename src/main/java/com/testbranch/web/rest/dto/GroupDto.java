package com.testbranch.web.rest.dto;

import com.testbranch.model.Certification;
import com.testbranch.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private long id;

    @NotBlank(message = "group name is required!")
    private String name;

    private Map<String, String> attributes;
    private Set<MemberDto> members;
    private CertificationDto certification;

}
