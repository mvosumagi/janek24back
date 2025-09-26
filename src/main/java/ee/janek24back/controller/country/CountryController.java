package ee.janek24back.controller.country;


import ee.janek24back.controller.country.dto.CountryDto;
import ee.janek24back.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryDto> findCountries() {
        return countryService.findCountries();
    }


}
