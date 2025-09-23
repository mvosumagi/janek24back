package ee.janek24back.controller.service;

import ee.janek24back.persistence.service.Service;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {
    @Mapping(source = "currencyIsId", target = "currencyIs.id")
    @Mapping(source = "serviceCategoryId", target = "serviceCategory.id")
    Service toEntity(ServiceController serviceController);

    @InheritInverseConfiguration(name = "toEntity")
    ServiceController toDto(Service service);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Service partialUpdate(ServiceController serviceController, @MappingTarget Service service);
}