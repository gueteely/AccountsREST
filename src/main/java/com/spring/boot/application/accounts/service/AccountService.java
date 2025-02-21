package com.spring.boot.application.accounts.service;


import com.spring.boot.application.accounts.entity.Account;
import com.spring.boot.application.accounts.exception.AccountNotFoundException;
import com.spring.boot.application.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long createAccount(String name, String email, Integer bill) {
        Account account = new Account(name, email, bill);
        return accountRepository.save(account).getId();
    }

    public Account getAccountById(Long id) {
        return accountRepository.
                findById(id).
                orElseThrow(() -> new AccountNotFoundException("Account not found with id " + id));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account deleteAccountById(Long id) {
        Account account = getAccountById(id);
        accountRepository.deleteById(id);
        return account;
    }
}
