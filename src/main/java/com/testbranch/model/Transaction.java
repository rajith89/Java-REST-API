package com.testbranch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionid")
    private Long id;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "transactiontype")
    private String transactiontype;

    @Column(name = "trasactiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trasactiondate;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;


}

