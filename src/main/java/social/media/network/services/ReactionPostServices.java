package social.media.network.services;

import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.ReactionCommentRequest;
import social.media.network.payload.dto.request.ReactionPostRequest;

public interface ReactionPostServices {
    MessageResponse reactionPost(ReactionPostRequest reactionPostRequest);


}
