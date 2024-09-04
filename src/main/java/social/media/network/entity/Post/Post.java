package social.media.network.entity.Post;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.Comment.Comment;
import social.media.network.entity.Reaction.ReactionPost;
import social.media.network.entity.User.User;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    private String title;

    private String content;

    @Column(updatable = false)
    private Date createdAt;

    private Date updateAt;

    @Enumerated(EnumType.STRING)
    private EPostStatus ePostStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<ReactionPost> reactionPostList;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;
}
