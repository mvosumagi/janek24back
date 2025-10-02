package ee.janek24back.persistence.servicecategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_category_image")
@Getter
@Setter
@NoArgsConstructor
public class ServiceCategoryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_category_id", nullable = false)
    private Integer serviceCategoryId;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    public ServiceCategoryImage(Integer serviceCategoryId, byte[] imageData) {
        this.serviceCategoryId = serviceCategoryId;
        this.imageData = imageData;
    }
}
