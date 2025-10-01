package ee.janek24back.controller.searchservice;

import ee.janek24back.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public List<ServiceInfo> search(@RequestParam String query) {
        return searchService.searchServices(query);
    }
}
