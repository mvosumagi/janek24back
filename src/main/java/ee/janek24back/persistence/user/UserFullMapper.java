package ee.janek24back.persistence.user;


import ee.janek24back.controller.user.dto.UserFullDto;
import ee.janek24back.persistence.address.Address;
import ee.janek24back.persistence.company.Company;
import io.micrometer.common.lang.Nullable;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserFullMapper {


    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phoneNo", target = "phoneNumber")
    @Mapping(source = "address.country.id", target = "countryId")
    @Mapping(source = "address.city.id", target = "cityId")
    @Mapping(source = "address.county", target = "state")
    @Mapping(source = "address.details", target = "address")
    @Mapping(source = "address.postalCode", target = "postalCode")
    @Mapping(expression = "java(company != null)", target = "isCompany")
    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "company.number", target = "regNo")
    UserFullDto toDto(User user, @Nullable Address address, @Nullable Company company);


    @AfterMapping
    default void applyDefaults(@MappingTarget UserFullDto dto) {
        if (dto.getCountryId() == null) dto.setCountryId(0);
        if (dto.getCityId() == null) dto.setCityId(0);
        if (dto.getPostalCode() == null) dto.setPostalCode("");
        if (dto.getIsCompany() == null) dto.setIsCompany(false);
    }
}