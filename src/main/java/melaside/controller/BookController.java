package melaside.controller;

import melaside.model.Book;
import melaside.model.Comment;
import melaside.model.MyUser;
import melaside.model.User;
import melaside.model.dto.BookDto;
import melaside.model.dto.OrderDto;
import melaside.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final OrderService orderService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping
    public String index(@RequestParam(value = "query", required = false) String query,
                        Model model, @PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC)Pageable pageable) {

        Page<Book> books = bookService.findPaginated(query, pageable);

        int totalPages = books.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("query", query);
        model.addAttribute("books", books);

        return "books";
    }


    @GetMapping("/new")
    public String newBook(BookDto bookDto, Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());

        return "book-form";
    }

    @RequestMapping("/new")
    public String bookForm(@Valid BookDto bookDto, BindingResult bindingResult,
                           Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());

        if (bindingResult.hasErrors()) {
            return "book-form";
        }

        long id = bookService.saveDto(bookDto);

        return "redirect:/book/new/" + id;
    }

    @GetMapping("/new/{id}")
    public String addPictureView(){
        return "book-editor";
    }

    @PostMapping("/new/{id}")
    public String addPicture(@PathVariable("id") Long id,
                             @RequestParam("file")MultipartFile file) throws IOException {

        if(file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(Path.of(uploadPath + File.separator + resultFileName));

            Book book = bookService.findById(id);
            book.setFileName(resultFileName);

            bookService.save(book);
        }

        return "redirect:/book/" + id;
    }


    @GetMapping("/{id}")
    public String bookPage(@AuthenticationPrincipal MyUser myUser,
                           @PathVariable("id") Long id, Model model,
                           @PageableDefault(direction = Sort.Direction.DESC, size = 5)Pageable pageable) {
        Book book = bookService.findById(id);
        Page<Comment> comments = commentService.findAllCommentsByBookId(id, pageable);

        int totalPages = comments.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

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
                             @PathVariable("id") Long id, Model model,
                             @PageableDefault(direction = Sort.Direction.DESC, size = 5)Pageable pageable) {

        User user = userService.findById(myUser.getId());
        Book book = bookService.findById(id);

        Page<Comment> comments = commentService.findAllCommentsByBookId(id, pageable);

        int totalPages = comments.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        Optional<User> userCurrent = Optional.ofNullable(userService.findById(myUser.getId()));
        userCurrent.ifPresent(value -> model.addAttribute(
                "books", value.getBooks().stream()
                        .map(Book::getId).collect(Collectors.toList())));

        commentService.createComment(user, book, text);

        model.addAttribute("comments", comments);
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
    public String bookPageRemove(@AuthenticationPrincipal MyUser myUser,
                                 @PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        User user = userService.findById(myUser.getId());

        bookService.deleteBookFromUser(book, user);

        return "redirect:/book/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);

        bookService.delete(book);

        return "redirect:/book";
    }

    @GetMapping("/order/{id}")
    public String orderForm(OrderDto orderDto){
        return "order-form";
    }

    @PostMapping("/order/{id}")
    public String orderBook(@Valid OrderDto dto,
                            @PathVariable("id") Long id,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal MyUser myUser){
        if(bindingResult.hasErrors()){
            return "order-form";
        } else {
            User user = userService.findById(myUser.getId());
            Book book = bookService.findById(id);

            orderService.createOrder(dto, book, user);
            return "redirect:/user/" + id;
        }
    }

}
