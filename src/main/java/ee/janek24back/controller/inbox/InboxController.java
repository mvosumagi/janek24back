package ee.janek24back.controller.inbox;

import ee.janek24back.controller.inbox.dto.InboxDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.service.InboxService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor


public class InboxController {

    private final InboxService inboxService;

    @GetMapping("/inbox")
    @Operation(
            summary = "Leiab süsteemist Inboxi jaosk andmed.",
            description = "Leiab süsteemist Inboxi jaosk andmed."
    )

    public InboxDto findInboxData(@RequestParam Integer userId) {
        return inboxService.findInboxData(userId);

    }
}
