package melaside.controller;

import melaside.exception.BookNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookAdviceController {

    @ExceptionHandler(BookNotFoundException.class)
    public String bookNotFound(){
        return "bookNotFound";
    }

}
