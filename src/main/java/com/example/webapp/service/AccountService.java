package com.example.webapp.service;

import com.example.webapp.model.Account;
import com.example.webapp.model.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> findById(Long id);
    List<AccountDto> findAll();
    void save(AccountDto accountDto);
}
