package ee.janek24back.controller.inbox.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Value
public class InboxDto implements Serializable {
    Integer id;
    Integer receiverUserId;
    String receiverUsername;
    String receiverUserFirstName;
    String receiverUserLastName;
    Integer senderUserId;
    String senderUsername;
    String senderUserFirstName;
    String senderUserLastName;
    @NotNull @Size(max = 100)
    String title;
    @NotNull @Size(max = 1000)
    String message;
    @NotNull @Size(max = 1)
    String status;
    @NotNull
    Instant createdAt;
}
