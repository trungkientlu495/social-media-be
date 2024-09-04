package social.media.network.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.media.network.entity.Comment.Comment;
import social.media.network.entity.Reaction.EReactionType;
import social.media.network.entity.Reaction.ReactionComment;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.ReactionCommentRequest;
import social.media.network.repository.CommentRepo;
import social.media.network.repository.ReactionCommentRepo;
import social.media.network.services.ReactionCommentServices;

@Service
@RequiredArgsConstructor
public class ReactionCommentServicesImpl implements ReactionCommentServices {
    private final AuthServicesImpl authServices;
    private final CommentRepo commentRepo;
    private final ReactionCommentRepo reactionCommentRepo;
    @Override
    public MessageResponse reactionComment(ReactionCommentRequest reactionCommentRequest) {
        User user = authServices.getAuth();
        Comment comment = commentRepo.findById(reactionCommentRequest.getIdComment()).orElseThrow(
                () -> new CustormException("Comment not found")
        );
        EReactionType abc;
        try {
            abc = EReactionType.valueOf(reactionCommentRequest.getTypeReaction());
        }catch (Exception e) {
            throw new CustormException("Invalid reaction type");
        }
        ReactionComment reactionComment = reactionCommentRepo.findReactionCommentBy(reactionCommentRequest.getIdComment());
        if (reactionComment == null) {
            ReactionComment newReactionComment1 = new ReactionComment();
            newReactionComment1.setComment(comment);
            newReactionComment1.setEReactionType(abc);
            reactionCommentRepo.save(newReactionComment1);
            return null;
        }
        if(reactionComment.getEReactionType().equals(abc)) {
            reactionCommentRepo.delete(reactionComment);
        }else{
            reactionComment.setEReactionType(abc);
            reactionCommentRepo.save(reactionComment);
        }

        return null;
    }
}
