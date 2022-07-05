package melaside.service.impl;

import melaside.model.Book;
import melaside.model.Comment;
import melaside.model.User;
import melaside.repository.CommentRepo;
import melaside.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Override
    public void createComment(User user, Book book, String text) {
        Comment comment = new Comment();

        comment.setUser(user);
        comment.setBook(book);
        comment.setMessage(text);

        commentRepo.save(comment);

    }

    @Override
    public Page<Comment> findAllCommentsByBookId(Long id, Pageable pageable) {
        return commentRepo.findAllCommentsByBookId(id, pageable);
    }
}
