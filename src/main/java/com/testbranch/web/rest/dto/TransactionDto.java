package com.testbranch.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long id;
    private Long accountid;
    private String reference;
    private Integer amount;
    private String transactiontype;
    private Date trasactiondate;
    private String status;
    private String description;

}
