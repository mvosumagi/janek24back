package ee.janek24back.persistence.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByNameIgnoreCase(String name);
    List<Country> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
}