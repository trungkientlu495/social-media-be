package social.media.network.payload.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import social.media.network.entity.Post.Post;
import social.media.network.payload.dto.request.PostRequest;
import social.media.network.payload.dto.response.PostResponse;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    Post toPost(PostRequest postRequest);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    PostResponse toPostResponse(Post post);
}
