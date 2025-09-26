package ee.janek24back.controller.user;

import ee.janek24back.controller.user.dto.UserDetailDto;
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

    @GetMapping("/user-small")
    @Operation(
            summary = "Tagastab user info UserId alusel",
            description = "Tagastab user info UserId alusel")
    public UserDto findUser(@RequestParam Integer userId) {
        return userService.findUser(userId);
    }

    @GetMapping("/user")
    @Operation(
            summary = "Tagastab user Detail info UserId alusel",
            description = "Tagastab user Detail info UserId alusel")
    public UserDto findUserDetail(@RequestParam Integer userId) {
        return userService.findUser(userId);
    }


    @GetMapping("/user/availability")
    @Operation(summary = "Kontrollib, kas kasutajanimi on vaba", description = "Tagastab boolean lipu 'available'")
    public UsernameAvailabilityResponseDto checkUsername(@RequestParam String username) {
        boolean available = userService.isUsernameAvailable(username);
        return new UsernameAvailabilityResponseDto(available);
    }

    @PostMapping("/user")
    @Operation(
            summary = "Lisab useri sisestatud andmete alusel",
            description = "Lisab useri sisestatud andmete alusel")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loodud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Mingi viga",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})

    public void addUser(@RequestBody @Valid UserDetailDto userDetailDto) {
        userService.addUser(userDetailDto);

    }
}


