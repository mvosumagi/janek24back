package ee.janek24back.persistence.user;

import ee.janek24back.controller.login.LoginResponse;
import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "role.id",   target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    UserDto toUserDto(User user);

    @InheritConfiguration(name = "toUserDto")
    UserDetailDto toUserDetailDto(User user);

    List<UserDetailDto> toUserDetailDtos(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
//    @Mapping(target = "status", expression = "java(Status.ACTIVE.getCode())")

    User toUser(UserDetailDto userDetailDto);
}
