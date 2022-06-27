package melaside.service;

import melaside.model.User;
import melaside.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Long save(UserDto dto);

    User findById(Long id);


}

