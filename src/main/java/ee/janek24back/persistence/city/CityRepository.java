package ee.janek24back.persistence.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("select c from City c where c.country.id = :countryId order by c.name")
    List<City> findCitiesBy(Integer countryId);

    Optional<City> findByNameIgnoreCaseAndCountry_Id(String name, Integer countryId);

    List<City> findAllByNameContainingIgnoreCaseAndCountry_IdOrderByNameAsc(String name, Integer countryId);
}
