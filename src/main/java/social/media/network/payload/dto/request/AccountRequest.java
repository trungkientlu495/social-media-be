package social.media.network.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "idUser cannot blank")
    private Long accountId;
    @NotBlank(message = "password cannot blank")
    private String passwordAccount;
}
