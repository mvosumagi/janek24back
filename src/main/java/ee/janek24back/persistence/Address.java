package ee.janek24back.persistence;

import ee.janek24back.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "janek24")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('janek24.address_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private ee.janek24back.persistence.Country country;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private ee.janek24back.persistence.City city;

    @Size(max = 255)
    @Column(name = "county")
    private String county;

    @Size(max = 255)
    @NotNull
    @Column(name = "details", nullable = false)
    private String details;

    @Size(max = 20)
    @NotNull
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone_no", nullable = false, length = 10)
    private String phoneNo;

    @Size(max = 1)
    @NotNull
    @Column(name = "type", nullable = false, length = 1)
    private String type;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

}