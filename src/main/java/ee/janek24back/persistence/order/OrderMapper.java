package ee.janek24back.persistence.order;

import ee.janek24back.controller.order.dto.OrderDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(source = "providerServiceId", target = "providerService.id")
    Order toOrder(OrderDto orderDto);


}