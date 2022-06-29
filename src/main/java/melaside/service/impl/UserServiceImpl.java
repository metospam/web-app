package melaside.service.impl;

import melaside.exception.UserNotFoundException;
import melaside.model.MyUser;
import melaside.model.Role;
import melaside.model.User;
import melaside.model.dto.UserDto;
import melaside.repository.UserRepo;
import melaside.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> currentUser = userRepo.findByUsername(username);

        return currentUser.map(user -> new MyUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getBooks(),
                createAuthorities(user.getRoles())
        )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Long saveDto(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList(new Role(2L, "ROLE_USER")));

        userRepo.save(user);

        return user.getId();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    private List<? extends GrantedAuthority> createAuthorities(List<Role> roles) {
        return ofNullable(roles)
                .map(roleList -> roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).
                        collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
