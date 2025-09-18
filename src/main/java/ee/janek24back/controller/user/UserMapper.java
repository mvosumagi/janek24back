package ee.janek24back.controller.user;
import ee.janek24back.persistence.user.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    @Mapping(source = "", target = "id")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "id")
    @Mapping(source = "", target = "id")
    @Mapping(source = "", target = "id")
    User toUserData(UserDto userDto);


}