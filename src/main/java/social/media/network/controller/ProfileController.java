package social.media.network.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import social.media.network.payload.dto.request.ProfileRequest;
import social.media.network.payload.ResponseHandler;
import social.media.network.services.Impl.CloudinaryServicesImpl;
import social.media.network.services.Impl.ProfileServicesImpl;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileServicesImpl profileServices;
    private final CloudinaryServicesImpl cloudinaryService;
    // idUser --> user view profile , idProfile --> profile thuoc user do
    @GetMapping("")
    public ResponseEntity<Object> takeProfile(@RequestParam("idProfile") Long idProfile) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,profileServices.takeProfile(idProfile));
    }
    // accessProfile : true -> public , accessProfile : false -> private
    @PutMapping("/access")
    public ResponseEntity<Object> accessProfile() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,profileServices.accessProfile());
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,profileServices.updateProfile(profileRequest));
    }

    @PutMapping("/update/change-avatar")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,cloudinaryService.upload(file).get("url"));
    }

}
