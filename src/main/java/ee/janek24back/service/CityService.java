package ee.janek24back.service;

import ee.janek24back.controller.city.dto.CityDto;
import ee.janek24back.persistence.city.City;
import ee.janek24back.persistence.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<CityDto> listByCountry(Integer countryId) {
        return cityRepository.findByCountry_IdOrderByNameAsc(countryId).stream()
                .map(c -> new CityDto(c.getId(), c.getName(), c.getCountry().getId()))
                .toList();
    }

    public CityDto findById(Integer id) {
        City c = cityRepository.findById(id).orElseThrow();
        return new CityDto(c.getId(), c.getName(), c.getCountry().getId());
    }

    public CityDto findByNameAndCountry(String name, Integer countryId) {
        City c = cityRepository.findByNameIgnoreCaseAndCountry_Id(name, countryId).orElseThrow();
        return new CityDto(c.getId(), c.getName(), c.getCountry().getId());
    }

    public List<CityDto> searchByNameAndCountry(String q, Integer countryId) {
        return cityRepository.findAllByNameContainingIgnoreCaseAndCountry_IdOrderByNameAsc(q, countryId).stream()
                .map(c -> new CityDto(c.getId(), c.getName(), c.getCountry().getId()))
                .toList();
    }
}
