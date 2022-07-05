package melaside.service;

import melaside.model.Book;
import melaside.model.Comment;
import melaside.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    void createComment(User user, Book book, String text);

    Page<Comment> findAllCommentsByBookId(Long id, Pageable pageable);

}
