package com.example.webapp.service;

import com.example.webapp.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();
    void save(UserDto accountDto);
}
