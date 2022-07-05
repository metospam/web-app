package melaside.service.impl;

import lombok.RequiredArgsConstructor;
import melaside.model.Book;
import melaside.model.Order;
import melaside.model.User;
import melaside.model.dto.OrderDto;
import melaside.repository.OrderRepo;
import melaside.service.OrderService;
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
}
