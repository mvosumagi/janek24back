package ee.janek24back.persistence;

import ee.janek24back.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "service", schema = "janek24")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('janek24.service_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_category_id", nullable = false)
    private ee.janek24back.persistence.ServiceCategory serviceCategory;

    @Size(max = 254)
    @NotNull
    @Column(name = "name", nullable = false, length = 254)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "description_short", nullable = false, length = 100)
    private String descriptionShort;

    @Size(max = 1000)
    @NotNull
    @Column(name = "description_long", nullable = false, length = 1000)
    private String descriptionLong;

    @NotNull
    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @NotNull
    @Column(name = "valid_to", nullable = false)
    private LocalDate validTo;

    @NotNull
    @Column(name = "unit_cost", nullable = false, precision = 7, scale = 2)
    private BigDecimal unitCost;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_is", nullable = false)
    private Currency currencyIs;

    @Size(max = 1)
    @NotNull
    @Column(name = "status", nullable = false, length = 1)
    private String status;

}