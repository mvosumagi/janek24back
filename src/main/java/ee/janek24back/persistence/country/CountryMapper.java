package ee.janek24back.persistence.country;

import ee.janek24back.controller.country.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {


    @Mapping(source = "id", target = "countryId")
    @Mapping(source = "name", target = "countryName")
    CountryDto toCountryDto(Country country);


    List<CountryDto> toCountryDtos(List<Country> countries);


}