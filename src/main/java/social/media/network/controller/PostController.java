package social.media.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.media.network.payload.ResponseHandler;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.services.Impl.PostServicesImpl;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostServicesImpl postServices;
    @PostMapping("/create")
    public ResponseEntity<Object> createPost(PostRequest postRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,postServices.createPost(postRequest));
    }

}
