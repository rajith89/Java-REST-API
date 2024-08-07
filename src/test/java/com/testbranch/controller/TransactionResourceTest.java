package com.testbranch.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbranch.service.TransactionService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.TransactionDto;
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
public class TransactionResourceTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionResource transactionResource;

    private TransactionDto transactionDto;

    @BeforeEach
    public void setUp() {
        transactionDto = new TransactionDto();
        // Set up your TransactionDto object here if needed
    }

    @Test
    public void testAddTransaction() {
        ResponseEntity<Void> responseEntity = transactionResource.addTransaction(transactionDto);
        verify(transactionService, times(1)).addransaction(any(TransactionDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllTransactions() {
        List<TransactionDto> transactions = new ArrayList<>();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        ResponseEntity<List<TransactionDto>> responseEntity = transactionResource.getAllTransactions();
        verify(transactionService, times(1)).getAllTransactions();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }

    @Test
    public void testGetAllTransactionById() throws ResourceNotFoundException {
        TransactionDto transaction = new TransactionDto();
        when(transactionService.getTranactionById(anyLong())).thenReturn(transaction);

        ResponseEntity<TransactionDto> responseEntity = transactionResource.getAllTransactionByid(1L);
        verify(transactionService, times(1)).getTranactionById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
    }
}
