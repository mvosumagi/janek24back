package ee.janek24back.persistence.order;

import ee.janek24back.controller.order.dto.OrderDto;
import ee.janek24back.persistence.providerservice.ProviderService;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(source = "providerServiceId", target = "providerService")
    Order toOrder(OrderDto orderDto);

    default ProviderService ProviderService(Integer id) {
        if (id == null) {
            return null;
        }
        ProviderService ps = new ProviderService();
        ps.setId(id);
        return ps;
    }
}
