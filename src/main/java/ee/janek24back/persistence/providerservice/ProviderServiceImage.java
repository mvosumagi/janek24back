package ee.janek24back.persistence.providerservice;

import ee.janek24back.persistence.teenus.Teenus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "provider_service_image")
public class ProviderServiceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "provider_service_id", nullable = false, unique = true)
    private Teenus teenus;
}
