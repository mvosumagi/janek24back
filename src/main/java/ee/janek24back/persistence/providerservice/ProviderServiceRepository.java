package ee.janek24back.persistence.providerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProviderServiceRepository extends JpaRepository<ProviderService, Integer> {

    @Query("""
            select p from ProviderService p
            where upper(p.name) like upper(concat('%', :serviceName, '%')) or upper(p.descriptionShort) like upper(concat('%', :description, '%'))""")
    List<ProviderService> findProviderServicesBy(String serviceName, String description);

}
