package social.media.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.media.network.payload.ResponseHandler;
import social.media.network.services.Impl.ReactionPostServicesImpl;

@RestController
@RequestMapping("/api/v1/reaction")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionPostServicesImpl reactionPostServices;
    @PostMapping("/post")
    public ResponseEntity<Object> reactionPost() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
    }

}
