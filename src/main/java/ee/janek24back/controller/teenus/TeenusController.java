package ee.janek24back.controller.teenus;

import ee.janek24back.controller.teenus.dto.TeenusDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.infrastructure.exception.ForbiddenException;
import ee.janek24back.persistence.address.Address;
import ee.janek24back.persistence.company.Company;
import ee.janek24back.persistence.role.Role;
import ee.janek24back.persistence.teenus.Teenus;
import ee.janek24back.persistence.teenus.TeenusMapper;
import ee.janek24back.persistence.teenus.TeenusRepository;
import ee.janek24back.persistence.user.User;
import ee.janek24back.service.TeenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
