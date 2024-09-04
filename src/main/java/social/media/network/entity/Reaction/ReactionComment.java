package social.media.network.entity.Reaction;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.Comment.Comment;

@Entity
@Data
public class ReactionComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idComment")
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private EReactionType eReactionType;
}
