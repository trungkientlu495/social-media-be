package social.media.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.media.network.payload.ResponseHandler;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.services.Impl.PostServicesImpl;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostServicesImpl postServices;
    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@RequestBody PostRequest postRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,postServices.createPost(postRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updatePost(@RequestBody PostRequest postRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,postServices.updatePost(postRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePost(@RequestBody PostRequest postRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,postServices.deletePost(postRequest));
    }

    @GetMapping("/suggest")
    public ResponseEntity<Object> suggestPosts(@RequestBody PostRequest postRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,postServices.suggestPost(postRequest));
    }

}
