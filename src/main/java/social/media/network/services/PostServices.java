package social.media.network.services;

import social.media.network.payload.dto.request.PostRequest;

public interface PostServices {
    Boolean createPost(PostRequest postRequest);
}
