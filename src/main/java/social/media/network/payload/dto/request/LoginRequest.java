package social.media.network.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email cannot blank")
    private String userEmail;

    @NotBlank(message = "password cannot blank")
    private String password;
}
