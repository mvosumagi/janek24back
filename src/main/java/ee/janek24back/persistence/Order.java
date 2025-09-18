package ee.janek24back.persistence;

import ee.janek24back.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "\"order\"", schema = "janek24")
public class Order {
    @Id
    @ColumnDefault("nextval('janek24.order_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ee.janek24back.persistence.Service service;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 1000)
    @NotNull
    @Column(name = "user_comment", nullable = false, length = 1000)
    private String userComment;

    @Size(max = 1)
    @NotNull
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @NotNull
    @Column(name = "confirmation_comment", nullable = false, length = Integer.MAX_VALUE)
    private String confirmationComment;

}