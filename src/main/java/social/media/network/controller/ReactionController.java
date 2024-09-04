package social.media.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.media.network.payload.ResponseHandler;
import social.media.network.payload.dto.request.ReactionCommentRequest;
import social.media.network.payload.dto.request.ReactionPostRequest;
import social.media.network.services.Impl.ReactionPostServicesImpl;
import social.media.network.services.ReactionCommentServices;

@RestController
@RequestMapping("/api/v1/reaction")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionPostServicesImpl reactionPostServices;
    private final ReactionCommentServices reactionCommentServices;
    @PostMapping("/post")
    public ResponseEntity<Object> reactionPost(@RequestBody ReactionPostRequest reactionPostRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,reactionPostServices.reactionPost(reactionPostRequest));
    }

    @PostMapping("/comment")
    public ResponseEntity<Object> reactionComment(@RequestBody ReactionCommentRequest reactionCommentRequest) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,reactionCommentServices.reactionComment(reactionCommentRequest));
    }

    @GetMapping("/post/list")
    public ResponseEntity<Object> listReactionPost() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }

    @GetMapping("/comment/list")
    public ResponseEntity<Object> listReactionComment() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }
}
