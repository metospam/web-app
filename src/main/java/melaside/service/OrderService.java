package melaside.service;

import melaside.model.Book;
import melaside.model.Order;
import melaside.model.User;
import melaside.model.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    void createOrder(OrderDto dto, Book book, User user);

    Page<Order> findAllOrdersByUserUsername(String query, Pageable pageable);
    void delete(Order order);
    Order findById(Long id);
}
