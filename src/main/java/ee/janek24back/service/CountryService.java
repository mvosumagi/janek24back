package ee.janek24back.service;

import ee.janek24back.controller.country.dto.CountryDto;
import ee.janek24back.persistence.country.Country;
import ee.janek24back.persistence.country.CountryMapper;
import ee.janek24back.persistence.country.CountryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryDto> findCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toCountryDtos(countries);
    }

    public CountryDto findById(Integer id) {
        Country c = countryRepository.findById(id).orElseThrow();
        return new CountryDto(c.getId(), c.getName());
    }

    public CountryDto findByName(String name) {
        Country c = countryRepository.findByNameIgnoreCase(name).orElseThrow();
        return new CountryDto(c.getId(), c.getName());
    }

    public List<CountryDto> searchByName(String q) {
        return countryRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(q).stream()
                .map(c -> new CountryDto(c.getId(), c.getName()))
                .toList();
    }
}
