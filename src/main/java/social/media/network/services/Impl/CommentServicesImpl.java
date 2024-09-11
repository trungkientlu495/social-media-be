package social.media.network.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import social.media.network.entity.Comment.Comment;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.repository.CommentRepo;
import social.media.network.services.CommentServices;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentServicesImpl implements CommentServices {
    private final CommentRepo commentRepo;
    @Override
    public Comment postComment(Comment comment) {
        Comment newComment = new Comment();
        newComment.setComment(comment.getComment());
        newComment.setCreatedAt(comment.getCreatedAt());
        //get info user qua token , session
        newComment.setUser(null);
        newComment.setPost(null);
        commentRepo.save(newComment);
        return newComment;
    }

    @Override
    public Comment editComment(Comment comment) {
        Comment newComment = commentRepo.getReferenceById(comment.getIdComment());
        if(newComment != null) {
            throw new CustormException("comment not found");
        }
        newComment.setComment(comment.getComment());
        newComment.setUpdatedAt(new Date());
        commentRepo.save(newComment);
        return newComment;
    }
}
