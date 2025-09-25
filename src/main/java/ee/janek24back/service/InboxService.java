package ee.janek24back.service;

import ee.janek24back.controller.inbox.dto.InboxDto;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.inbox.Inbox;
import ee.janek24back.persistence.inbox.InboxMapper;
import ee.janek24back.persistence.inbox.InboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InboxService {

    private final InboxRepository inboxRepository;
    private final InboxMapper inboxMapper;

    public InboxDto findInboxData(Integer userId) {
        Inbox inbox = inboxRepository.findInboxInfo(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));

        return inboxMapper.toDto(inbox);
    }
}
