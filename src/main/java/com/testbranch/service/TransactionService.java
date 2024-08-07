package com.testbranch.service;

import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    void addransaction(TransactionDto transactionDto);

    List<TransactionDto> getAllTransactions();

    TransactionDto getTranactionById(long id) throws ResourceNotFoundException;
}
