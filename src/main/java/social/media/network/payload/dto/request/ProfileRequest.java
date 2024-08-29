package social.media.network.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import social.media.network.config.custorm_anonation.config.ValidEmail;

import java.time.LocalDate;
@Data
public class ProfileRequest {

    @NotBlank(message = "firstName not blank")
    private String firstName;

    @NotBlank(message = "lastName not blank")
    private String lastName;

    //@NotBlank(message = "lastName not blank")
    private LocalDate dob;

    private String avatar;

    private String homeTown;

    private String schoolName;

    private String workPlace;

    private Boolean isProfilePublic=true;
}
