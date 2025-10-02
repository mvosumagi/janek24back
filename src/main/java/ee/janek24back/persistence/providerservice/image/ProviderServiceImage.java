package ee.janek24back.persistence.providerservice.image;

import ee.janek24back.persistence.providerservice.ProviderService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "provider_service_image", schema = "janek24")
public class ProviderServiceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "provider_service_id", nullable = false, unique = true)
    private ProviderService providerService;
}
