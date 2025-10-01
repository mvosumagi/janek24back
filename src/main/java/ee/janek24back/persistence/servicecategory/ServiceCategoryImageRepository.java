package ee.janek24back.persistence.servicecategory;

public interface ServiceCategoryImageRepository extends JpaRepository<ServiceCategoryImage, Integer> {
    List<ServiceCategoryImage> findAll();
}
