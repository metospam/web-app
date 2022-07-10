package melaside.controller;

import melaside.exception.OrderNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdviceController {

    @ExceptionHandler(OrderNotFoundException.class)
    public String orderNotFound(){

        return "orderNotFound";
    }
}
