package ee.janek24back.persistence.inbox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InboxRepository extends JpaRepository<Inbox, Integer> {
    @EntityGraph(attributePaths = {"receiverUser","senderUser"})
    Page<Inbox> findAllByReceiverUser_IdOrderByCreatedAtDesc(Integer receiverUserId, Pageable pageable);
    @EntityGraph(attributePaths = {"receiverUser","senderUser"})
    Optional<Inbox> findByIdAndReceiverUser_Id(Integer id, Integer receiverUserId);
    long countByReceiverUser_IdAndStatus(Integer receiverUserId, String status);
}
