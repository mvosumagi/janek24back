package ee.janek24back.controller.address.dto;

import ee.janek24back.Status;
import ee.janek24back.controller.address.AddressDto;
import ee.janek24back.persistence.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface AddressMapper {

}
