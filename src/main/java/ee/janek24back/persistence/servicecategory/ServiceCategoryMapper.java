package ee.janek24back.persistence.servicecategory;

import ee.janek24back.controller.servicecategory.dto.ServiceCategoryDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceCategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    ServiceCategoryDto toServiceCategoryDto(ServiceCategory serviceCategory);

    List<ServiceCategoryDto> toServiceCategoryDtos(List<ServiceCategory> serviceCategories);
}