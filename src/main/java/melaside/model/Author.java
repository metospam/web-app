package melaside.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors", schema = "edu_schema")
@Getter @Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_author_id_seq")
    @SequenceGenerator(name = "authors_author_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author", orphanRemoval = true)
    private List<Book> books;
}
