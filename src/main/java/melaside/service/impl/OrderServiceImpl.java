package melaside.service.impl;

import lombok.RequiredArgsConstructor;
import melaside.exception.OrderNotFoundException;
import melaside.model.Book;
import melaside.model.Order;
import melaside.model.User;
import melaside.model.dto.OrderDto;
import melaside.repository.OrderRepo;
import melaside.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public void createOrder(OrderDto dto, Book book, User user) {
        Order order = new Order();

        order.setInitials(dto.getInitials());
        order.setAddress(dto.getAddress());
        order.setPhone(dto.getPhone());
        order.setOrderDate(new Date());
        order.setUser(user);
        order.setBook(book);

        orderRepo.save(order);
    }

    @Override
    public Page<Order> findAllOrdersByUserUsername(String query, Pageable pageable) {
        if (query == null) {
            return orderRepo.findAll(pageable);
        } else {
            return orderRepo.findAllOrdersByUserUsername(query, pageable);
        }
    }

    @Override
    public void delete(Order order) {
        orderRepo.delete(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
