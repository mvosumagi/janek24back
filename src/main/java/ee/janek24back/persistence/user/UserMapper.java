package ee.janek24back.persistence.user;

import ee.janek24back.Status;
import ee.janek24back.controller.login.LoginResponse;
import ee.janek24back.controller.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNo", target = "phoneNumber")
    UserDto toUserDto(User user);

    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNo")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    User toUser(UserDto userDto);
}
