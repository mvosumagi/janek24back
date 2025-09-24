package ee.janek24back.persistence.user;

import ee.janek24back.Status;
import ee.janek24back.controller.login.LoginResponse;
import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.persistence.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "username",   target = "username")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNo", target = "phoneNumber")
    @Mapping(ignore = true, target = "password")
    UserDto toUserDto(User user);

    @Mapping(source = "username", target = "username" )
    @Mapping(source = "password", target = "password" )
    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    @Mapping(source = "email", target = "email" )
    @Mapping(source = "phoneNumber", target = "phoneNo" )
    @Mapping(source = "firstName", target = "firstName" )
    @Mapping(source = "lastName", target = "lastName" )
    User toUser(UserDetailDto userDetailDto);
}
