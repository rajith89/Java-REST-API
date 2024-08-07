package com.testbranch.web.rest.dto;

import com.testbranch.constants.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String name;
    private String organization;
    private Subscription subscription;
    private UserDto user;

}
