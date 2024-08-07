package com.testbranch.service.impl;

import com.testbranch.model.Group;
import com.testbranch.model.Transaction;
import com.testbranch.repository.TransactionRepository;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.GroupDto;
import com.testbranch.web.rest.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("transactionService")
public class TransactionServiceImpl implements com.testbranch.service.TransactionService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void addransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        transactionRepository.save(transaction);
    }

    public List<TransactionDto> getAllTransactions(){
        List<Transaction> transactionList =transactionRepository.findAll();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        if(!transactionList.isEmpty()) {
            transactionDtoList = transactionList.stream().map(p -> {
                TransactionDto transactionDto = new TransactionDto();
                BeanUtils.copyProperties(p, transactionDto);
                return transactionDto;
            }).collect(Collectors.toList());
        }
        return transactionDtoList;
    }


    public TransactionDto getTranactionById(long id) throws ResourceNotFoundException {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            LOGGER.error("transaction by id :{} not found.", id);
            throw new ResourceNotFoundException("Members by group id :{} not found.");
        }
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction.get(), transactionDto);
        return transactionDto;
    }
}
