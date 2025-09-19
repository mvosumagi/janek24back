package ee.janek24back.persistence.user;


import ee.janek24back.controller.login.LoginResponse;
import ee.janek24back.controller.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "id",        target = "id")
    @Mapping(source = "role.id",   target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "username",  target = "username")
    @Mapping(source = "status",    target = "status")
    UserDto toUserDto(User user);


}
