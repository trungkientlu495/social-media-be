package social.media.network.payload.dto.request;

import lombok.Data;
import social.media.network.config.custorm_anonation.config.PasswordMatches;
import social.media.network.config.custorm_anonation.config.StrongPassword;
import social.media.network.config.custorm_anonation.config.ValidEmail;

@Data
@PasswordMatches(password = "newPassword",confirmPassword = "confirmPassword")
public class ChangePasswordRequest {

    private String oldPassword;

    @StrongPassword
    private String newPassword;

    private String confirmPassword;
}
