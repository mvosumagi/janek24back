package ee.janek24back.persistence.servicecategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ServiceCategoryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer serviceCategoryId;

    @Lob
    private byte[] imageData;
}
