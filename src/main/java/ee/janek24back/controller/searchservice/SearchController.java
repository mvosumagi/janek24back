package ee.janek24back.controller.searchservice;

import ee.janek24back.controller.providerservice.ProviderServiceInfo;
import ee.janek24back.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<ProviderServiceInfo>> search(@RequestParam String query) {
        List<ProviderServiceInfo> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}
