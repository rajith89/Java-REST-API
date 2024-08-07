package com.testbranch.service.impl;

import com.testbranch.model.Account;
import com.testbranch.model.User;
import com.testbranch.repository.AccountRepository;
import com.testbranch.repository.UserRepository;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.AccountDto;
import com.testbranch.web.rest.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("accountService")
public class AccountServiceImpl implements com.testbranch.service.AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        AccountDto accountDtoReturn = new AccountDto();
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        account = accountRepository.save(account);
        BeanUtils.copyProperties(account, accountDtoReturn);
        return accountDtoReturn;
    }

    @Override
    public AccountDto getAccountById(Long id) {
         Account accountOptional = accountRepository.findById(id).orElse(null);
         Optional<User> user = userRepository.findById(accountOptional.getUser().getId());
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user.get(), userDto);
        AccountDto accountDto = AccountDto.builder().id(accountOptional.getId()).name(accountOptional.getName())
                .organization(accountOptional.getOrganization()).subscription(accountOptional.getSubscription()).user(userDto).build();
        return accountDto;
    }

    @Override
    public AccountDto updateAccount(long id, AccountDto accountDto) throws ResourceNotFoundException {
        Account account = new Account();
        AccountDto accountDtoReturn = new AccountDto();
        Optional<Account> exitingAccount = accountRepository.findById(id);
        if(exitingAccount.isPresent()){
            BeanUtils.copyProperties(accountDto, account);
            account = accountRepository.save(account);
            BeanUtils.copyProperties(account, accountDtoReturn);
        }else{
            LOGGER.error("Account id : {} not found.", id);
            throw new ResourceNotFoundException("Account id :{} not found.");
        }
        return accountDtoReturn;
    }


    @Override
    public void deleteAccount(long id) throws ResourceNotFoundException{
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            accountRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Account not found to delete.");
        }
    }






}
