package ee.janek24back.controller.inbox.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class SendInboxRequest {
    @NotNull Integer senderUserId;
    @NotNull Integer receiverUserId;
    @NotBlank @Size(max = 100)
    String title;
    @NotBlank @Size(max = 1000)
    String message;
}
