package social.media.network.entity.User;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "blocking_user_id")
    private User blockingUser;

    @ManyToOne
    @JoinColumn(name = "blocked_user_id")
    private User blockedUser;
}
//block 1 block -> nhieu user blick , 1 block