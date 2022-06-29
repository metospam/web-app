package melaside.controller;

import melaside.model.Book;
import melaside.model.Comment;
import melaside.model.MyUser;
import melaside.model.User;
import melaside.model.dto.BookDto;
import melaside.service.AuthorService;
import melaside.service.BookService;
import melaside.service.CommentService;
import melaside.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final CommentService commentService;
    private final AuthorService authorService;


    @GetMapping
    public String index() {
        return "books";
    }


    @GetMapping("/new")
    public String newBook(BookDto bookDto) {
        return "book-form";
    }

    @RequestMapping("/new")
    public String bookForm(@Valid BookDto bookDto, BindingResult bindingResult,
                           Model model) {
        model.addAttribute("authors", authorService.findAll());
        if (bindingResult.hasErrors()) {
            return "book-form";
        }

        long id = bookService.save(bookDto);

        return "redirect:/book/" + id;
    }


    @GetMapping("/{id}")
    public String bookPage(@AuthenticationPrincipal MyUser myUser,
                           @PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        List<Comment> comments = commentService.findAllCommentsByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments);

        if (myUser == null) {
            return "bookPage";
        }

        Optional<User> user = Optional.ofNullable(userService.findById(myUser.getId()));

        user.ifPresent(value -> model.addAttribute(
                "books", value.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toList())));

        return "bookPageAuth";
    }

    @PostMapping("/{id}")
    public String updateBook(@RequestParam("text") String text,
                             @AuthenticationPrincipal MyUser myUser,
                             @PathVariable("id") Long id, Model model) {

        User user = userService.findById(myUser.getId());
        Book book = bookService.findById(id);

        Optional<User> userCurrent = Optional.ofNullable(userService.findById(myUser.getId()));
        userCurrent.ifPresent(value -> model.addAttribute(
                "books", value.getBooks().stream()
                        .map(Book::getId).collect(Collectors.toList())));

        commentService.createComment(user, book, text);

        model.addAttribute("comments", commentService.findAllCommentsByBookId(id));
        model.addAttribute("book", book);

        return "bookPageAuth";
    }

    @GetMapping("/subscribe/{id}")
    public String bookPageAdd(@AuthenticationPrincipal MyUser myUser,
                              @PathVariable("id") Long id) {

        Book book = bookService.findById(id);
        User user = userService.findById(myUser.getId());

        bookService.addBookToUser(book, user);

        return "redirect:/book/" + id;
    }

    @GetMapping("/unsubscribe/{id}")
    public String bookPageRemove(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        bookService.deleteBookFromUser(book);

        return "redirect:/book/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        bookService.delete(book);

        return "redirect:/book";
    }
}
