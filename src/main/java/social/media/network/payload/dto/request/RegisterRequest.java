package social.media.network.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import social.media.network.config.custorm_anonation.config.PasswordMatches;
import social.media.network.config.custorm_anonation.config.StrongPassword;
import social.media.network.config.custorm_anonation.config.ValidEmail;

@Data
@PasswordMatches(password = "password",confirmPassword = "confirmPassword")
public class RegisterRequest {
    @NotBlank(message = "firstName cannot blank")
    private String firstName;

    @NotBlank(message = "lastName cannot blank")
    private String lastName;

    @ValidEmail
    private String userEmail;

    @StrongPassword
    private String password;

    private String confirmPassword;

    private Integer otp;
}
