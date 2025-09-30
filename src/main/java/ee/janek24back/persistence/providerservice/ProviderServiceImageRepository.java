package ee.janek24back.persistence.providerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderServiceImageRepository extends JpaRepository<ProviderServiceImage, Long> {
    Optional<ProviderServiceImage> findByTeenusId(Long teenusId);

    void deleteByTeenusId(Long teenusId);
}