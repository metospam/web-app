package melaside.repository;

import melaside.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    Page<Comment> findAllCommentsByBookId(Long id, Pageable pageable);


}
