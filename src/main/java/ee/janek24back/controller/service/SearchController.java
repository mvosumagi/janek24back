package ee.janek24back.controller.service;

import ee.janek24back.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/services/search")
    @Operation(
            summary = "Leiab kõik serviced otsides osa description_short.",
            description = "Leiab kõik serviced otsides osa description_short."
    )
    public List<ServiceInfo> searchServices(@RequestParam String partialDescription) {
        return searchService.searchServices(partialDescription);
    }
}