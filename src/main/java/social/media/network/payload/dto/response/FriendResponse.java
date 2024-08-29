package social.media.network.payload.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import social.media.network.entity.User.EStatusUser;

import java.time.LocalDate;
@Data
public class FriendResponse {
    private Long userId;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private String avatar;

    private String homeTown;

    private String schoolName;

    private String workPlace;

}
