package melaside.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "edu_schema")
@Getter @Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
