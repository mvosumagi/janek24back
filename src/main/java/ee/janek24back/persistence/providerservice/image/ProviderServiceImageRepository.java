package ee.janek24back.persistence.providerservice.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderServiceImageRepository extends JpaRepository<ProviderServiceImage, Integer> {
}
