package ee.janek24back.controller.service;

import ee.janek24back.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * DTO for {@link ee.janek24back.persistence.service.Service}
 */
@RestController
@RequiredArgsConstructor

public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("/my-service")
    @Operation(summary = "Leiab süsteemist (andmebaasist service tabelist) kõik serviced.",
            description = "Tagastab info koos serviceId ja serviceName'ga")

//    public ServiceInfo findService(@RequestParam Integer serviceId) {
//        return serviceService.findService(serviceId);
//    }


    public List<ServiceInfo> findServices() {
        return Collections.singletonList(serviceService.findService());
    }
}