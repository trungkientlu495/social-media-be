package social.media.network.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import social.media.network.config.custorm_anonation.config.PasswordMatches;

import java.util.Date;

@PasswordMatches(password = "newPassword",confirmPassword = "confirmPassword")
@Data
public class ForgotPasswordRequest {

    @NotBlank(message = "email cannot blank")
    private String userEmail;

    private String newPassword;

    private String confirmPassword;

    private Integer otp;

    private Date otpExpiryTime;
    //private int count;
}
