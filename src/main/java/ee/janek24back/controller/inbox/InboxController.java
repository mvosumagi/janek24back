package ee.janek24back.controller.inbox;

import ee.janek24back.controller.inbox.dto.InboxDto;
import ee.janek24back.controller.inbox.dto.SendInboxRequest;
import ee.janek24back.controller.inbox.dto.UpdateStatusRequest;
import ee.janek24back.controller.inbox.dto.UsernameSendRequest;
import ee.janek24back.service.InboxService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inbox")
public class InboxController {
    private final InboxService inboxService;

    @GetMapping
    public Page<InboxDto> list(@RequestParam Integer receiverUserId, Pageable pageable) {
        return inboxService.list(receiverUserId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InboxDto send(@Valid @RequestBody SendInboxRequest req) {
        return inboxService.send(req);
    }

    @PostMapping("/compose-usernames")
    @ResponseStatus(HttpStatus.CREATED)
    public List<InboxDto> composeUsernames(@Valid @RequestBody UsernameSendRequest req) {
        return inboxService.composeByUsernames(req);
    }

    @PutMapping("/{id}/status")
    public InboxDto setStatus(@PathVariable Integer id,
                              @RequestParam Integer receiverUserId,
                              @Valid @RequestBody UpdateStatusRequest req) {
        return inboxService.updateStatus(id, receiverUserId, req.getStatus());
    }

    @GetMapping("/unread-count")
    public CountDto unreadCount(@RequestParam Integer receiverUserId) {
        long count = inboxService.unreadCount(receiverUserId);
        return new CountDto(count);
    }

    public record CountDto(long count) {}
}
