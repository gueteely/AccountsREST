package com.spring.boot.application.accounts.controller;

import com.spring.boot.application.accounts.controller.dto.AccountRequestDTO;
import com.spring.boot.application.accounts.controller.dto.AccountResponseDTO;
import com.spring.boot.application.accounts.exception.AccountNotFoundException;
import com.spring.boot.application.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/accounts")
    public Long createAccount(@RequestBody AccountRequestDTO accountRequestDAO) {
        return accountService.createAccount(accountRequestDAO.getName(), accountRequestDAO.getEmail(),
                accountRequestDAO.getBill());
    }

    @GetMapping("/accounts/{id}")
    public AccountResponseDTO getAccount(@PathVariable Long id) {
        return new AccountResponseDTO(accountService.getAccountById(id));
    }

    @GetMapping("/accounts")
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts()
                .stream()
                .map(AccountResponseDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/accounts/{id}")
    public AccountResponseDTO deleteAccount(@PathVariable Long id) {
        return new AccountResponseDTO(accountService.deleteAccountById(id));
    }
}
