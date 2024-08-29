package social.media.network.payload.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import social.media.network.entity.User.User;
import social.media.network.payload.dto.response.FriendResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface FriendMapper {
    FriendMapper INSTANCE = Mappers.getMapper(FriendMapper.class);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "dob", source = "dob")
    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "homeTown", source = "homeTown")
    @Mapping(target = "schoolName", source = "schoolName")
    @Mapping(target = "workPlace", source = "workPlace")
    FriendResponse toUser(User user);

    default List<FriendResponse> toUsers(List<User> users) {
        return users.stream().map(this::toUser).collect(Collectors.toList());
    }

}
