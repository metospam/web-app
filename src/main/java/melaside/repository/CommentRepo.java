package melaside.repository;

import melaside.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllCommentsByBookId(Long id);
}
