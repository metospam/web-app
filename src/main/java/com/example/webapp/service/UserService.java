package com.example.webapp.service;

import com.example.webapp.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Long save(UserDto dto);
}

