package ee.janek24back.controller.service;

import ee.janek24back.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<ServiceInfo> findServices() {
        return serviceService.findServices();
    }

}