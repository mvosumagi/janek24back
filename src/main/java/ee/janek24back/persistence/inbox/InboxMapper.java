package ee.janek24back.persistence.inbox;

import ee.janek24back.controller.inbox.dto.InboxDto;
import ee.janek24back.persistence.inbox.Inbox;
import org.springframework.stereotype.Component;

@Component
public class InboxMapper {
    public InboxDto toDto(Inbox in) {
        if (in == null) return null;
        return new InboxDto(
                in.getId(),
                in.getReceiverUser()!=null ? in.getReceiverUser().getId() : null,
                in.getReceiverUser()!=null ? in.getReceiverUser().getUsername() : null,
                in.getReceiverUser()!=null ? in.getReceiverUser().getFirstName() : null,
                in.getReceiverUser()!=null ? in.getReceiverUser().getLastName() : null,
                in.getSenderUser()!=null ? in.getSenderUser().getId() : null,
                in.getSenderUser()!=null ? in.getSenderUser().getUsername() : null,
                in.getSenderUser()!=null ? in.getSenderUser().getFirstName() : null,
                in.getSenderUser()!=null ? in.getSenderUser().getLastName() : null,
                in.getTitle(),
                in.getMessage(),
                in.getStatus(),
                in.getCreatedAt()
        );
    }
}
