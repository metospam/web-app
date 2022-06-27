package melaside.service;

import melaside.model.Book;
import melaside.model.Comment;
import melaside.model.User;

import java.util.List;

public interface CommentService {

    void createComment(User user, Book book, String text);

    List<Comment> findAllCommentsByBookId(Long id);

}
