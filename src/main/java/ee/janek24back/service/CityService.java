package ee.janek24back.service;

import ee.janek24back.controller.city.dto.CityDto;
import ee.janek24back.persistence.city.City;
import ee.janek24back.persistence.city.CityMapper;
import ee.janek24back.persistence.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityDto> findCitiesBy(Integer countryId) {
        List<City> cities = cityRepository.findCitiesBy(countryId);
        return cityMapper.toCityDtos(cities);
    }

}
