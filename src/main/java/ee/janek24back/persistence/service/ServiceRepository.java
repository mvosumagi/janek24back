package ee.janek24back.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByNameContainingIgnoreCaseOrDescriptionShortContainingIgnoreCase(String name, String description);

}
