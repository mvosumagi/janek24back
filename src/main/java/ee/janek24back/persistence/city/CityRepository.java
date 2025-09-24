package ee.janek24back.persistence.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByCountry_IdOrderByNameAsc(Integer countryId);
    Optional<City> findByNameIgnoreCaseAndCountry_Id(String name, Integer countryId);
    List<City> findAllByNameContainingIgnoreCaseAndCountry_IdOrderByNameAsc(String name, Integer countryId);
}
