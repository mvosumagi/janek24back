package ee.janek24back.controller.user;

import ee.janek24back.controller.user.dto.PasswordUpdate;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.controller.user.dto.UsernameAvailabilityResponseDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @Operation(
            summary = "Lisab useri sisestatud andmete alusel",
            description = "Lisab useri sisestatud andmete alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "kasutaja edukalt süsteemi lisatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "The username is already taken (errorCode = 132132)", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addUser(@RequestParam String username, @RequestParam String password, @RequestBody @Valid UserDto userDto) {
        userService.addUser(username, password, userDto);
    }


    @PutMapping("/user")
    public void updateUser(@RequestParam Integer userId, @RequestBody @Valid UserDto userDto) {
        userService.updateUser(userId, userDto);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody @Valid PasswordUpdate passwordUpdate) {
        userService.updatePassword(passwordUpdate);
    }

    @GetMapping("/user")
    @Operation(summary = "Tagastab kasutaja profiili", description = "Pärib kasutaja täisprofiili ID alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Userit ei leitud", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public UserDto getUser(@RequestParam Integer userId) {
        return userService.getUser(userId);
    }


    @GetMapping("/user/availability")
    @Operation(summary = "Kontrollib, kas kasutajanimi on vaba", description = "Tagastab boolean lipu 'available'")
    public UsernameAvailabilityResponseDto checkUsername(@RequestParam String username) {
        boolean available = userService.isUsernameAvailable(username);
        return new UsernameAvailabilityResponseDto(available);
    }


}


