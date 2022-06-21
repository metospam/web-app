package com.example.webapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", schema = "edu_schema")
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", schema = "edu_schema", allocationSize = 1)
    Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            schema = "edu_schema",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_users",
            schema = "edu_schema",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books;
}
