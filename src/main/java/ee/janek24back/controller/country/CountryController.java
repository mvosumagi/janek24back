package ee.janek24back.controller.country;


import ee.janek24back.controller.country.dto.CountryDto;
import ee.janek24back.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryDto> all() {
        return countryService.list();
    }


}
