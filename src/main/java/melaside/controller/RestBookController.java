package melaside.controller;

import lombok.RequiredArgsConstructor;
import melaside.model.Book;
import melaside.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class RestBookController {

    private final BookService bookService;

    @GetMapping(value = "/table",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> table(@RequestParam(value = "q", required = false) String query){
        return bookService.search(query);
    }


}
