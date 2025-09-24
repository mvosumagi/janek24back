package ee.janek24back.service;

import ee.janek24back.persistence.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}