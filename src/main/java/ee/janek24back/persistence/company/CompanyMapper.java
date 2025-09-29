package ee.janek24back.persistence.company;

import ee.janek24back.controller.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "companyName", target = "name")
    @Mapping(source = "regNo", target = "number")
    Company toCompany(UserDto userDto);
}