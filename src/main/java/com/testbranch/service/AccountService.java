package com.testbranch.service;

import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto updateAccount(long id, AccountDto accountDto) throws ResourceNotFoundException;

    void deleteAccount(long id) throws ResourceNotFoundException;
}
