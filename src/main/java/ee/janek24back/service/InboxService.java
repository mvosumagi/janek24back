package ee.janek24back.service;

import ee.janek24back.controller.inbox.dto.InboxDto;
import ee.janek24back.controller.inbox.dto.SendInboxRequest;
import ee.janek24back.controller.inbox.dto.UsernameSendRequest;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.inbox.Inbox;
import ee.janek24back.persistence.inbox.InboxMapper;
import ee.janek24back.persistence.inbox.InboxRepository;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class InboxService {
    private final InboxRepository inboxRepo;
    private final UserRepository userRepo;
    private final InboxMapper mapper;

    @Transactional(readOnly = true)
    public Page<InboxDto> list(Integer receiverUserId, Pageable pageable) {
        return inboxRepo.findAllByReceiverUser_IdOrderByCreatedAtDesc(receiverUserId, pageable)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public long unreadCount(Integer receiverUserId) {
        return inboxRepo.countByReceiverUser_IdAndStatus(receiverUserId, "N");
    }

    public InboxDto send(SendInboxRequest req) {
        User sender = userRepo.findById(req.getSenderUserId())
                .orElseThrow(() -> new PrimaryKeyNotFoundException("senderUserId", req.getSenderUserId()));
        User receiver = userRepo.findById(req.getReceiverUserId())
                .orElseThrow(() -> new PrimaryKeyNotFoundException("receiverUserId", req.getReceiverUserId()));
        Inbox e = new Inbox();
        e.setReceiverUser(receiver);
        e.setSenderUser(sender);
        e.setTitle(req.getTitle());
        e.setMessage(req.getMessage());
        e.setStatus("N");
        e.setCreatedAt(Instant.now());
        return mapper.toDto(inboxRepo.save(e));
    }

    public List<InboxDto> composeByUsernames(UsernameSendRequest req) {
        User sender = userRepo.findByUsernameIgnoreCase(req.getFrom())
                .orElseThrow(() -> new IllegalArgumentException("Sender username not found: " + req.getFrom()));

        Set<String> toSet = parseList(req.getTo());
        Set<String> ccSet = parseList(req.getCc());
        Set<String> all = new LinkedHashSet<>();
        all.addAll(toSet);
        all.addAll(ccSet);
        all.remove(req.getFrom().toLowerCase(Locale.ROOT));

        if (all.isEmpty()) throw new IllegalArgumentException("No valid recipients");

        List<User> receivers = userRepo.findAllByUsernameIgnoreCaseIn(all);
        if (receivers.isEmpty()) throw new IllegalArgumentException("Recipients not found: " + all);

        List<InboxDto> out = new ArrayList<>();
        for (User r : receivers) {
            Inbox e = new Inbox();
            e.setReceiverUser(r);
            e.setSenderUser(sender);
            e.setTitle(req.getSubject());
            e.setMessage(req.getBody());
            e.setStatus("N");
            e.setCreatedAt(Instant.now());
            out.add(mapper.toDto(inboxRepo.save(e)));
        }
        return out;
    }

    public InboxDto updateStatus(Integer inboxId, Integer receiverUserId, String status) {
        Inbox e = inboxRepo.findByIdAndReceiverUser_Id(inboxId, receiverUserId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("inboxId", inboxId));
        e.setStatus(status);
        return mapper.toDto(e);
    }

    private Set<String> parseList(String s) {
        Set<String> out = new LinkedHashSet<>();
        if (s == null || s.isBlank()) return out;
        String[] parts = s.split("[,;\\s]+");
        for (String p : parts) {
            String v = p.trim().toLowerCase(Locale.ROOT);
            if (!v.isEmpty()) out.add(v);
        }
        return out;
    }
}
