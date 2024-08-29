package social.media.network.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.media.network.payload.ResponseHandler;
import social.media.network.payload.dto.request.*;
import social.media.network.services.Impl.AuthServicesImpl;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServicesImpl authServices;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginDTO) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,authServices.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest register) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,authServices.registerUser(register));
    }

    @PostMapping("/register/check-otp")
    public ResponseEntity<Object> registerOtp(@RequestBody RegisterRequest register) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,authServices.registerCheckOTP(register));
    }

    @GetMapping("/account/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS,HttpStatus.OK,authServices.logOut(request));
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<Object> deleteAccount(@RequestBody AccountRequest accountDTO) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS,HttpStatus.OK,authServices.deleteAccount(accountDTO));
    }

    @PostMapping("/account/forgot-password")
    public ResponseEntity<Object> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS,HttpStatus.OK,authServices.forgotPassword(forgotPasswordRequest));
    }

    @PostMapping("/account/forgot-password/check-otp")
    public ResponseEntity<Object> forgotPasswordOTP(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS,HttpStatus.OK,authServices.forgotPasswordOTP(forgotPasswordRequest));
    }

    @PutMapping("/account/change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS,HttpStatus.OK,authServices.changePassword(changePasswordRequest));
    }

}
