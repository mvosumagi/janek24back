package ee.janek24back.controller.city;
import ee.janek24back.controller.city.dto.CityDto;
import ee.janek24back.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CityController {
    private final CityService cityService;

    @GetMapping("/api/countries/{countryId}/cities")
    public List<CityDto> byCountry(@PathVariable Integer countryId) {
        return cityService.listByCountry(countryId);
    }

}
