package ee.janek24back.persistence.user;


import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.controller.user.dto.UserFullDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserFullMapper {


    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNo", target = "phoneNumber")
    UserDto toUserDto(User user);


    @AfterMapping
    default void applyDefaults(@MappingTarget UserFullDto dto) {
        if (dto.getCountryId() == null) dto.setCountryId(0);
        if (dto.getCityId() == null) dto.setCityId(0);
        if (dto.getPostalCode() == null) dto.setPostalCode("");
        if (dto.getIsCompany() == null) dto.setIsCompany(false);
    }
}