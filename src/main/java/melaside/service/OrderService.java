package melaside.service;

import melaside.model.Book;
import melaside.model.User;
import melaside.model.dto.OrderDto;

public interface OrderService {

    void createOrder(OrderDto dto, Book book, User user);
}
