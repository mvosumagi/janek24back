package ee.janek24back.persistence.service;

import ee.janek24back.controller.service.ServiceInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "name", target = "serviceName")
    @Mapping(source = "descriptionShort", target = "descriptionShort")
    @Mapping(source = "unitCost", target = "unitCost")
    ServiceInfo toServiceInfo (Service service);

    List<ServiceInfo> toServiceInfos (List<Service> services);


}