package hello.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "genres", schema = "edu_schema")
@Getter @Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres_genre_id_seq")
    @SequenceGenerator(name = "genres_genre_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
