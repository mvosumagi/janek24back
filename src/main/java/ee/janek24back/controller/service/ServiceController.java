package ee.janek24back.controller.service;

import ee.janek24back.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/my-service")
    @Operation(
            summary = "Leiab süsteemist (andmebaasist service tabelist) kõik serviced.",
            description = "Tagastab info koos serviceId ja serviceName'ga"
    )
    public List<ServiceInfo> findServices() {
        return serviceService.findServices();
    }

    @GetMapping("/services/search")
    public List<ServiceInfo> searchServices(@RequestParam String q) {
        return serviceService.searchServices(q);
    }
}