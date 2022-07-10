package melaside.repository;

import melaside.model.Order;
import melaside.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {

    @Query("SELECT b FROM Order b WHERE b.initials LIKE %?1%")
    Page<Order> findAllOrdersByUserUsername(String query, Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByUser(Pageable pageable, User user);

}
