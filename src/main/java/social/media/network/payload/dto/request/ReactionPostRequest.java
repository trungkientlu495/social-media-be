package social.media.network.payload.dto.request;

import lombok.Data;

@Data
public class ReactionPostRequest {
    String typeReaction;
    Integer idPost;
}

