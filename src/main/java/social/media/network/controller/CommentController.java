package social.media.network.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.media.network.payload.ResponseHandler;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @PostMapping("/post")
    public ResponseEntity<Object> postComment() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editComment() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteComment() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listComment() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }
}
