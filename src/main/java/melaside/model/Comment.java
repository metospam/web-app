package melaside.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments", schema = "edu_schema")
@Getter @Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id_seq")
    @SequenceGenerator(name = "comments_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    Book book;

    @ManyToOne
    User user;
}
