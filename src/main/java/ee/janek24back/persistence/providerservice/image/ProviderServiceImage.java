package ee.janek24back.persistence.providerservice.image;

import jakarta.persistence.*;

@Entity
@Table(name = "provider_service_image")
public class ProviderServiceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "provider_service_id", nullable = false)
    private Integer providerServiceId;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    public ProviderServiceImage() {}

    public ProviderServiceImage(Integer providerServiceId, byte[] imageData) {
        this.providerServiceId = providerServiceId;
        this.imageData = imageData;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProviderServiceId() {
        return providerServiceId;
    }

    public void setProviderServiceId(Integer providerServiceId) {
        this.providerServiceId = providerServiceId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
