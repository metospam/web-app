package melaside.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books", schema = "edu_schema")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_book_id_seq")
    @SequenceGenerator(name = "books_book_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinTable(
            name = "books_users",
            schema = "edu_schema",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private User user;

    @ManyToOne
    @JoinTable(
            name = "books_genres",
            schema = "edu_schema",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private Genre genre;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "book", orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
}
