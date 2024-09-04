package social.media.network.entity.Comment;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.Post.Post;
import social.media.network.entity.Reaction.ReactionComment;
import social.media.network.entity.User.User;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComment;

    @JoinColumn(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany
    private List<ReactionComment> reactionCommentList;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post post;


}
