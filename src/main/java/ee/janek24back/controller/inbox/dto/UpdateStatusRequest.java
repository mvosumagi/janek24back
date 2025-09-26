package ee.janek24back.controller.inbox.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class UpdateStatusRequest {
    @Pattern(regexp = "N|R|A")
    String status;
}
