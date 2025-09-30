package ee.janek24back.controller.teenus;

import ee.janek24back.controller.teenus.dto.TeenusDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.persistence.teenus.TeenusMapper;
import ee.janek24back.persistence.teenus.TeenusRepository;
import ee.janek24back.service.TeenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeenusController {

    private final TeenusService teenusService;
    private final TeenusRepository teenusRepository;
    private final TeenusMapper teenusMapper;

    @PostMapping("/teenus")
    @Operation(
            summary = "Lisab teenue sisestatud andmete alusel",
            description = "Lisab teenue sisestatud andmete alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "teenus edukalt süsteemi lisatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addTeenus(@RequestParam Integer userId, @RequestBody @Valid TeenusDto teenusDto) {
        teenusService.addTeenus(userId, teenusDto);
    }

    @GetMapping("/teenused")
    @Operation(summary = "Tagastab kasutaja teenused")
    public List<TeenusDto> getUserTeenused(@RequestParam Integer userId) {
        return teenusService.getUserTeenused(userId);
    }
}
