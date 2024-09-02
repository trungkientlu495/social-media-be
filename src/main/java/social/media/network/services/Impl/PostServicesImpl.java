package social.media.network.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import social.media.network.entity.Post.EPostStatus;
import social.media.network.entity.Post.Post;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.payload.dto.response.PostResponse;
import social.media.network.payload.mapping.PostMapper;
import social.media.network.repository.PostRepo;
import social.media.network.services.PostServices;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServicesImpl implements PostServices {
    private final AuthServicesImpl authServices;
    private final PostRepo postRepo;

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        User user = authServices.getAuth();
        Post post = PostMapper.INSTANCE.toPost(postRequest);
        post.setCreatedAt(new Date());
        post.setUser(user);
        switch (postRequest.getPostStatus()) {
            case null:
                throw new CustormException("Post Status null");
            case "PUBLIC":
                post.setEPostStatus(EPostStatus.PUBLIC);
                break;
            case "FRIEND":
                post.setEPostStatus(EPostStatus.FRIEND);
                break;
            case "PRIVATE":
                post.setEPostStatus(EPostStatus.PRIVATE);
                break;
            default:
                throw new CustormException("Input Post Status is not valid");
        }
        postRepo.save(post);
        PostResponse postResponse = PostMapper.INSTANCE.toPostResponse(post);
        postResponse.setPostStatus(postRequest.getPostStatus());
        return postResponse;
    }

    @Override
    public MessageResponse updatePost(PostRequest postRequest) {
        User user = authServices.getAuth();
        Post post = postRepo.findById(postRequest.getId()).orElseThrow(
                () -> new CustormException("Invalid Post Id")
        );
        if(!user.getUserId().equals(post.getUser().getUserId())) {
            throw new CustormException("no permission to edit posts");
        }
        try{
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setUpdateAt(new Date());
            postRepo.save(post);
            return new MessageResponse("Update Post Successfully");
        }
        catch(Exception e){
            return new MessageResponse("Update Post Failed");
        }
    }

    @Override
    public MessageResponse deletePost(PostRequest postRequest) {
        User user = authServices.getAuth();
        Post post = postRepo.findById(postRequest.getId()).orElseThrow(
                () -> new CustormException("Invalid Post Id")
        );
        if(!user.getUserId().equals(post.getUser().getUserId())) {
            throw new CustormException("no permission to delete posts");
        }
        postRepo.delete(post);
        return new MessageResponse("Delete Post Successfully");
    }

    @Override
    public MessageResponse suggestPost(PostRequest postRequest) {
        return null;
    }
}
