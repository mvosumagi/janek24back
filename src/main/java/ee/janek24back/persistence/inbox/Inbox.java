package ee.janek24back.persistence.inbox;

import ee.janek24back.persistence.order.Order;
import ee.janek24back.persistence.service.Service;
import ee.janek24back.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "inbox", schema = "janek24")
public class Inbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private User receiverUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_user_id", nullable = false)
    private User senderUser;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 1000)
    @NotNull
    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Size(max = 1)
    @NotNull
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}