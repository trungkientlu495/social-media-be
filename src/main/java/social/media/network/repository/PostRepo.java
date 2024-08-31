package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.media.network.entity.Post.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
