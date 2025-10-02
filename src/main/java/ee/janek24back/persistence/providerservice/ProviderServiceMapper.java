package ee.janek24back.persistence.providerservice;

import ee.janek24back.controller.providerservice.dto.ProviderServiceDto;
import ee.janek24back.controller.providerservice.ProviderServiceInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProviderServiceMapper {

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "serviceCategory.id", target = "categoryId")
    @Mapping(source = "name", target = "serviceName")
    @Mapping(source = "descriptionShort", target = "descriptionShort")
    @Mapping(source = "unitCost", target = "unitCost")
    ProviderServiceInfo toServiceInfo(ProviderService providerService);

    List<ProviderServiceInfo> toServiceInfos(List<ProviderService> providerServices);

    @Mapping(source = "currencyIsId", target = "currencyIs.id")
    @Mapping(source = "serviceCategoryId", target = "serviceCategory.id")
    ProviderService toProviderService(ProviderServiceDto providerServiceDto);

    @Mapping(source = "currencyIs.id", target = "currencyIsId")
    @Mapping(source = "serviceCategory.id", target = "serviceCategoryId")
    ProviderServiceDto toProviderServiceDto(ProviderService providerService);
}
