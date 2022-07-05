package melaside.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders", schema = "edu_schema")
@Getter @Setter
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq", schema = "edu_schema", allocationSize = 1)
    private Long id;

    @Column(name = "initials")
    private String initials;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "orderdate")
    private Date orderDate;

    @ManyToOne
    private User user;

    @OneToOne
    private Book book;

}
