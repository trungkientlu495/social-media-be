package social.media.network.payload.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import social.media.network.entity.User.User;
import social.media.network.payload.dto.request.RegisterRequest;

@Mapper
public interface RegisterMapper {
    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "password", source = "password")
    User toUser(RegisterRequest register);
}
