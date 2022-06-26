package hello.controller;

import hello.exception.StorageFileNotFoundException;
import hello.exception.UserNotFoundException;
import hello.model.User;
import hello.model.dto.UserDto;
import hello.repository.UserRepo;
import hello.service.StorageService;
import hello.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final StorageService storageService;

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + (file).getFilename() + "\"").body(file);
    }


    @GetMapping("/{id}")
    public String editContact(@PathVariable("id") Long id, Model model)  {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("id", user.getId());
        model.addAttribute("books", user.getBooks());
        model.addAttribute("username", user.getUsername());

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(UserController.class,
                                "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "edit-user";
    }

    @PostMapping("/{id}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "edit-user";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/registration")
    public String newUser(UserDto userDto) {
        return "user-form";
    }

    @PostMapping("/registration")
    public String createAccount(@Valid UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user-form";
        }
        userService.save(userDto);
        return "redirect:/login";
    }

}
