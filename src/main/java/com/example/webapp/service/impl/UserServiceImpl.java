package com.example.webapp.service.impl;

import com.example.webapp.model.Role;
import com.example.webapp.model.User;
import com.example.webapp.model.dto.UserDto;
import com.example.webapp.repository.UserRepo;
import com.example.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> currentUser =  userRepo.findByUsername(username);

        return currentUser.map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                        createAuthorities(user.getRoles())
                )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepo.findById(id).map(this::map);
    }

    @Override
    public List<UserDto> findAll() {
        return Streamable.of(userRepo.findAll()).map(this::map).toList();
    }

    @Transactional
    public void save(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepo.save(user);
    }

    private List<? extends GrantedAuthority> createAuthorities(List<Role> roles){
        return ofNullable(roles)
                .map(roleList -> roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).
                        collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    public UserDto map(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
