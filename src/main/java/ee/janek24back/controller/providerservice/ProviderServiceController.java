package ee.janek24back.controller.providerservice;

import ee.janek24back.controller.providerservice.dto.ProviderServiceDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.persistence.providerservice.ProviderServiceMapper;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
import ee.janek24back.service.ProviderServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProviderServiceController {

    private final ProviderServiceService providerServiceService;
    private final ProviderServiceRepository providerServiceRepository;
    private final ProviderServiceMapper providerServiceMapper;

    @PostMapping("/service")
    @Operation(
            summary = "Lisab teenue sisestatud andmete alusel",
            description = "Lisab teenue sisestatud andmete alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "teenus edukalt süsteemi lisatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addProviderService(@RequestParam Integer userId, @RequestBody @Valid ProviderServiceDto providerServiceDto) {
        providerServiceService.addProviderService(userId, providerServiceDto);
    }

    @PutMapping("/service")
    @Operation(
            summary = "uuendab teenuse sisestatud andmete alusel",
            description = "uuendab teenue sisestatud andmete alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "teenus edukalt uuendatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void updateProviderService(@RequestParam Integer providerServiceId, @RequestBody @Valid ProviderServiceDto providerServiceDto) {
        providerServiceService.updateProviderService(providerServiceId, providerServiceDto);
    }

    @GetMapping("/service")
    @Operation(
            summary = "tagastab teenuse providerServiceID alusel", description = "tagastab teenuse providerServiceID alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "teenus edukalt leitud"),
            @ApiResponse(responseCode = "404", description = "teenust ei leitud", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public ProviderServiceDto getProviderService(@RequestParam Integer providerServiceId) {
        return providerServiceService.getProviderServiceById(providerServiceId);
    }

    @GetMapping("/services")
    @Operation(summary = "Tagastab kasutaja teenused")
    public List<ProviderServiceDto> getUserProviderServices(@RequestParam Integer userId) {
        return providerServiceService.getUserProviderServices(userId);
    }

    @GetMapping("/services/search")
    @Operation(
            summary = "Leiab kõik serviced otsides osa description_short.",
            description = "Leiab kõik serviced otsides osa description_short."
    )
    public List<ProviderServiceInfo> searchServices(@RequestParam String partialDescription) {
        return providerServiceService.searchServices(partialDescription);
    }

    @GetMapping("/services/{serviceId}")
    public ProviderServiceInfo getServiceById(@PathVariable Integer serviceId) {
        return providerServiceService.getServiceById(serviceId);
    }


    @GetMapping("/service/{providerServiceId}/image")
    @Operation(summary = "Tagastab teenuse pildi")
    public ResponseEntity<byte[]> getServiceImage(@PathVariable Integer providerServiceId) {
        byte[] imageData = providerServiceService.getProviderServiceImage(providerServiceId);

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
