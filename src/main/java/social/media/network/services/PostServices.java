package social.media.network.services;

import social.media.network.entity.Post.Post;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.payload.dto.response.PostResponse;

public interface PostServices {
    PostResponse createPost(PostRequest postRequest);

    MessageResponse updatePost(PostRequest postRequest);

    MessageResponse deletePost(PostRequest postRequest);
}
