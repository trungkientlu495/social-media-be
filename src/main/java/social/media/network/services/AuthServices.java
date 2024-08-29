package social.media.network.services;

import jakarta.servlet.http.HttpServletRequest;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.*;

public interface AuthServices {
    //register User and register checkOTP
    MessageResponse registerUser(RegisterRequest register);

    MessageResponse registerCheckOTP(RegisterRequest register);

    Object login(LoginRequest loginDTO);

    MessageResponse logOut(HttpServletRequest request);

    MessageResponse deleteAccount(AccountRequest accountDTO);

    MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordDTO);

    MessageResponse forgotPasswordOTP(ForgotPasswordRequest forgotPasswordDTO);

    MessageResponse changePassword(ChangePasswordRequest changePasswordDTO);


}
