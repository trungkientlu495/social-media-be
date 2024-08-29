package social.media.network.entity.FriendShip;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.User.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EFriendshipStatus eFriendshipStatus = EFriendshipStatus.PENDING;

    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_send")
    private User userSendRequest;

    @ManyToOne
    @JoinColumn(name = "user_recevived")
    private User userReceivedRequest;
}
