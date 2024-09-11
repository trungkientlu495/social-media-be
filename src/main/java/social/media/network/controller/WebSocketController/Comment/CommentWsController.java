package social.media.network.controller.WebSocketController.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import social.media.network.entity.Comment.Comment;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.repository.CommentRepo;
import social.media.network.services.Impl.AuthServicesImpl;
import social.media.network.services.Impl.CommentServicesImpl;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentWsController {
    private final CommentServicesImpl commentServices;
    @MessageMapping("/comment/post")
    @SendTo("/topic/comment")
    public Comment postComment(@Payload Comment comment) {
        return commentServices.postComment(comment);
    }

    @MessageMapping("/comment/edit")
    @SendTo("/topic/comment")
    public Comment editComment(@Payload Comment comment) {
       return commentServices.editComment(comment);
    }


}
