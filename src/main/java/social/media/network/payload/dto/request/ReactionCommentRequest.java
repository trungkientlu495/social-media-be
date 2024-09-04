package social.media.network.payload.dto.request;

import lombok.Data;

@Data
public class ReactionCommentRequest {
        String typeReaction;
        Integer idComment;
}
