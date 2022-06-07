package com.example.webapp.repository;

import com.example.webapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
