package social.media.network.entity.Post;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.User.User;

import java.util.Date;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    private String title;

    private String content;

    private Date createdAt;

    private Date updateAt;

    @Enumerated(EnumType.STRING)
    private EPostStatus ePostStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
