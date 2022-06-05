package com.example.webapp.service.impl;

import com.example.webapp.model.Account;
import com.example.webapp.model.dto.AccountDto;
import com.example.webapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import com.example.webapp.repo.AccountRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final ModelMapper modelMapper;

    @Override
    public Optional<AccountDto> findById(Long id) {
        return accountRepo.findById(id).map(this::map);
    }

    @Override
    public List<AccountDto> findAll() {
        return Streamable.of(accountRepo.findAll()).map(this::map).toList();
    }

    @Override
    public void save(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setLogin(accountDto.getLogin());
        account.setPassword(accountDto.getPassword());

        accountRepo.save(account);
    }

    public AccountDto map(Account account){
        return modelMapper.map(account, AccountDto.class);
    }
}
