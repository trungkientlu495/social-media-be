package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import social.media.network.entity.Comment.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
