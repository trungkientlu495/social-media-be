package social.media.network.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.Post.Post;
import social.media.network.entity.User.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
