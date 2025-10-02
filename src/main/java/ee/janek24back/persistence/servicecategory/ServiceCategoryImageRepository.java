package ee.janek24back.persistence.servicecategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCategoryImageRepository extends JpaRepository<ServiceCategoryImage, Integer> {
    List<ServiceCategoryImage> findAll();
}