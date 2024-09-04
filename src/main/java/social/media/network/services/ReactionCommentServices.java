package social.media.network.services;

import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.ReactionCommentRequest;

public interface ReactionCommentServices {
    MessageResponse reactionComment(ReactionCommentRequest reactionCommentRequest);
}
