package com.testbranch.controller;

import com.testbranch.service.TransactionService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/")
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody TransactionDto transactionDto)  {
        transactionService.addransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getAllTransactionByid(long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(transactionService.getTranactionById(id));
    }


}
