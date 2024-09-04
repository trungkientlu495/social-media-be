package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.Reaction.ReactionComment;

public interface ReactionCommentRepo extends JpaRepository<ReactionComment, Integer> {
    @Query("SELECT u FROM ReactionComment u WHERE u.comment.idComment = :idComment")
    ReactionComment findReactionCommentBy(@Param("idComment") Integer idComment);
}
