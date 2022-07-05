package melaside.controller;

import lombok.RequiredArgsConstructor;
import melaside.model.Book;
import melaside.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final BookService bookService;

    @GetMapping
    public String index(Model model){
        Book firstBook = bookService.findAll().get(new Random().nextInt(bookService.findAll().size()));
        Book secondBook = bookService.findAll().get(new Random().nextInt(bookService.findAll().size()));
        Book thirdBook = bookService.findAll().get(new Random().nextInt(bookService.findAll().size()));

        model.addAttribute("firstBook", firstBook);
        model.addAttribute("secondBook", secondBook);
        model.addAttribute("thirdBook", thirdBook);


        return "home";
    }


}
