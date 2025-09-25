package ee.janek24back.persistence.inbox;

import ee.janek24back.controller.inbox.dto.InboxDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface InboxMapper {
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "serviceName", target = "service.name")
    @Mapping(source = "serviceId", target = "service.id")
    @Mapping(source = "senderUserLastName", target = "senderUser.lastName")
    @Mapping(source = "senderUserFirstName", target = "senderUser.firstName")
    @Mapping(source = "senderUserEmail", target = "senderUser.email")
    @Mapping(source = "senderUserId", target = "senderUser.id")
    @Mapping(source = "receiverUserLastName", target = "receiverUser.lastName")
    @Mapping(source = "receiverUserFirstName", target = "receiverUser.firstName")
    @Mapping(source = "receiverUserEmail", target = "receiverUser.email")
    @Mapping(source = "receiverUserId", target = "receiverUser.id")
    Inbox toDto(InboxDto inboxDto);

    @InheritInverseConfiguration(name = "toEntity")InboxDto toDto(Inbox inbox);

    @InheritConfiguration(name = "toEntity")@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Inbox partialUpdate(InboxDto inboxDto, @MappingTarget Inbox inbox);
}