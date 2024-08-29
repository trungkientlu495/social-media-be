package social.media.network.services.Impl;

import org.springframework.stereotype.Service;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.services.PostServices;

@Service
public class PostServicesImpl implements PostServices {
    @Override
    public Boolean createPost(PostRequest postRequest) {
        return true;
    }
}
