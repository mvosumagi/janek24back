package ee.janek24back.persistence.address;

import ee.janek24back.controller.address.dto.AddressDto;
import ee.janek24back.controller.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(source = "state", target = "county")
    @Mapping(source = "address", target = "details")
    @Mapping(constant = "H", target = "type")
    Address toAddress(UserDto userDto);

    @Mapping(source = "details", target = "address")
    AddressDto toDto(Address address);

}