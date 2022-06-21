package com.example.webapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter @Setter
public class MyUser extends User {

    private Long id;

    private List<Book> books;

    public MyUser(Long id, String username, String password,
                  List<Book> books, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.books = books;
    }

}
