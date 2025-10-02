package ee.janek24back.persistence.providerservice.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provider_service_image")
@Getter
@Setter
@NoArgsConstructor
public class ProviderServiceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "provider_service_id", nullable = false)
    private Integer providerServiceId;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    public ProviderServiceImage(Integer providerServiceId, byte[] imageData) {
        this.providerServiceId = providerServiceId;
        this.imageData = imageData;
    }
}
