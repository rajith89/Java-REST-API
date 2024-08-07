package com.testbranch.service;

import com.testbranch.constants.Subscription;
import com.testbranch.model.Account;
import com.testbranch.model.User;
import com.testbranch.repository.AccountRepository;
import com.testbranch.repository.UserRepository;
import com.testbranch.service.impl.AccountServiceImpl;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.AccountDto;
import com.testbranch.web.rest.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    private Account account;
    private AccountDto accountDto;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1L);
        account.setName("Test Account");
        account.setOrganization("Test Org");
        account.setSubscription(Subscription.FREE);

        accountDto = new AccountDto();
        accountDto.setId(1L);
        accountDto.setName("Test Account");
        accountDto.setOrganization("Test Org");
        accountDto.setSubscription(Subscription.FREE);

        user = new User();
        user.setId(1L);
        user.setEmail("test1@gmail.com");


        userDto = new UserDto();
        userDto.setId(1L);
        user.setEmail("test1@gmail.com");

        account.setUser(user);
        accountDto.setUser(userDto);
    }

    @Test
    void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto createdAccount = accountService.createAccount(accountDto);

        verify(accountRepository, times(1)).save(any(Account.class));
        assertEquals(accountDto.getName(), createdAccount.getName());
        assertEquals(accountDto.getOrganization(), createdAccount.getOrganization());
    }

    @Test
    void testGetAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        AccountDto fetchedAccount = accountService.getAccountById(1L);

        verify(accountRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        assertEquals(accountDto.getName(), fetchedAccount.getName());
       // assertEquals(accountDto.getUser().getEmail(), fetchedAccount.getUser().getEmail());
    }

    @Test
    void testUpdateAccount() throws ResourceNotFoundException {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto updatedAccount = accountService.updateAccount(1L, accountDto);

        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(any(Account.class));
        assertEquals(accountDto.getName(), updatedAccount.getName());
    }

    @Test
    void testUpdateAccount_ResourceNotFoundException() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.updateAccount(1L, accountDto));

        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteAccount() throws ResourceNotFoundException {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAccount_ResourceNotFoundException() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.deleteAccount(1L));

        verify(accountRepository, times(1)).findById(1L);
    }
}