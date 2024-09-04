package social.media.network.entity.Reaction;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.Post.Post;
import social.media.network.entity.User.User;

@Entity
@Data
public class ReactionPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Enumerated(EnumType.STRING)
    private EReactionType eReactionType;
}
