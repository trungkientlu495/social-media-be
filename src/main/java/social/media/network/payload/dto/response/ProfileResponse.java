package social.media.network.payload.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import social.media.network.config.custorm_anonation.config.ValidEmail;

import java.time.LocalDate;
@Data
public class ProfileResponse {


    @NotBlank(message = "firstName not blank")
    private String firstName;

    @NotBlank(message = "lastName not blank")
    private String lastName;


    private LocalDate dob;

    private String avatar;

    private String homeTown;

    private String schoolName;

    private String workPlace;

    private Boolean isProfilePublic=true;
}
