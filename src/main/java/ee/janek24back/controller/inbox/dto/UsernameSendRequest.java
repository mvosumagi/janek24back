package ee.janek24back.controller.inbox.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UsernameSendRequest {
    @NotBlank @Size(max = 255)
    String from;
    @NotBlank @Size(max = 2000)
    String to;
    @Size(max = 2000)
    String cc;
    @NotBlank @Size(max = 100)
    String subject;
    @NotBlank @Size(max = 1000)
    String body;
}
