package ee.janek24back.persistence.teenus;

import ee.janek24back.controller.teenus.dto.TeenusDto;
import ee.janek24back.persistence.providerservice.ProviderServiceImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeenusMapper {
    @Mapping(source = "currencyIsId", target = "currencyIs.id")
    @Mapping(source = "serviceCategoryId", target = "serviceCategory.id")
    Teenus toTeenus(TeenusDto teenusDto);

    @Mapping(source = "currencyIs.id", target = "currencyIsId")
    @Mapping(source = "serviceCategory.id", target = "serviceCategoryId")
    TeenusDto toTeenusDto(Teenus teenus);


}