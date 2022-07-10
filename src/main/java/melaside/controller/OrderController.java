package melaside.controller;

import lombok.RequiredArgsConstructor;
import melaside.model.Book;
import melaside.model.MyUser;
import melaside.model.Order;
import melaside.model.User;
import melaside.model.dto.OrderDto;
import melaside.repository.OrderRepo;
import melaside.service.BookService;
import melaside.service.OrderService;
import melaside.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final BookService bookService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderRepo orderRepo;
    @GetMapping
    public String index(@RequestParam(value = "query", required = false) String query,
                        Model model, @PageableDefault(size = 5, sort = {"id"},
                                direction = Sort.Direction.ASC) Pageable pageable){
        Page<Order> orders = orderService.findAllOrdersByUserUsername(query, pageable);

        int totalPages = orders.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("query", query);
        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("/{id}")
    public String orderForm(@PathVariable("id") Long id,
                            OrderDto orderDto, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "order-form";
    }

    @PostMapping("/{id}")
    public String orderBook(@Valid OrderDto dto, Model model,
                            @PathVariable("id") Long id,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal MyUser myUser){
        if(bindingResult.hasErrors()){
            return "order-form";
        }
        User user = userService.findById(myUser.getId());
        Book book = bookService.findById(id);

        model.addAttribute("book", book);

        orderService.createOrder(dto, book, user);

        return "redirect:/order/my";
    }

    @GetMapping("/my")
    public String myOrders(@AuthenticationPrincipal MyUser myUser,
                           Model model, @PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable){
        User user = userService.findById(myUser.getId());

        Page<Order> orders = orderRepo.findAllByUser(pageable, user);

        model.addAttribute("orders", orders);

        int totalPages = orders.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "myOrders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        Order order = orderService.findById(id);

        orderService.delete(order);

        return "redirect:/order";
    }

}
