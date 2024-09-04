package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.Reaction.ReactionPost;

public interface ReactionPostRepo extends JpaRepository<ReactionPost, Integer> {
    @Query("SELECT u FROM ReactionPost u WHERE u.post.idPost = :idPost")
    ReactionPost getReactionPostsByPost(@Param("idPost") int idPost);
}
