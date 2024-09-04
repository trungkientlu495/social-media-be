package social.media.network.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import social.media.network.entity.Post.Post;
import social.media.network.entity.Reaction.EReactionType;
import social.media.network.entity.Reaction.ReactionPost;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.ReactionPostRequest;
import social.media.network.repository.PostRepo;
import social.media.network.repository.ReactionPostRepo;
import social.media.network.services.ReactionPostServices;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactionPostServicesImpl implements ReactionPostServices {
    private final ReactionPostRepo reactionPostRepo;
    private final PostRepo postRepo;
    private final AuthServicesImpl authServices;
    @Override
    public MessageResponse reactionPost(ReactionPostRequest reactionPostRequest) {
        User user = authServices.getAuth();
        Post post = postRepo.findById(reactionPostRequest.getIdPost()).orElseThrow(
                () -> new CustormException("Post not found")
        );
        EReactionType abc;
        try {
            abc = EReactionType.valueOf(reactionPostRequest.getTypeReaction());
        }catch (Exception e) {
            throw new CustormException("Invalid reaction type");
        }
        ReactionPost reactionPost = reactionPostRepo.getReactionPostsByPost(reactionPostRequest.getIdPost());
        if (reactionPost == null) {
            ReactionPost newReactionPost = new ReactionPost();
            newReactionPost.setPost(post);
            newReactionPost.setUser(user);
            newReactionPost.setEReactionType(abc);
            reactionPostRepo.save(newReactionPost);
            return null;
        }
        if(reactionPost.getEReactionType().equals(abc)) {
            log.info("MMMMMM");
            reactionPostRepo.delete(reactionPost);
        }else{
            log.info("ABCD"+reactionPost.getEReactionType());
            reactionPost.setEReactionType(abc);
            reactionPostRepo.save(reactionPost);
        }

        return null;
    }
}
