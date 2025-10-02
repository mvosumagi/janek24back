package ee.janek24back.persistence.providerservice.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderServiceImageRepository extends JpaRepository<ProviderServiceImage, Integer> {

    @Query("select p from ProviderServiceImage p where p.providerService.id = :providerServiceId")
    Optional<ProviderServiceImage> findByProviderServiceId(Integer providerServiceId);
}

