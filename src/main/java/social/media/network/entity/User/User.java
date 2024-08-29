package social.media.network.entity.User;

import jakarta.persistence.*;
import lombok.Data;
import social.media.network.entity.FriendShip.FriendShip;
import social.media.network.entity.Post.Post;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String userEmail;

    private String password;

    private String oldPassword;

    private LocalDate dob;

    private String phoneNumber;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private EStatusUser status;

    private String homeTown;

    private String schoolName;

    private String workPlace;

    private Boolean isDelete = false;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private Boolean isProfilePublic = true;

    @OneToMany(mappedBy = "blockingUser")
    private List<Block> blockings;

    @OneToMany(mappedBy = "blockedUser")
    private List<Block> blockeds;

    @OneToMany(mappedBy = "userSendRequest")
    private List<FriendShip> sendRequests;

    @OneToMany(mappedBy = "userReceivedRequest")
    private List<FriendShip> receivedRequests;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
