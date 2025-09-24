package ee.janek24back.controller.user;

import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
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

    @GetMapping("/user")
    @Operation(
            summary = "Tagastab user info UserId alusel",
            description = "Tagastab user info UserId alusel")
    public UserDto findUser(@RequestParam Integer userId) {
        return userService.findUser(userId);
    }

    @PostMapping("/user")
    @Operation(
            summary = "Lisab useri sisestatud andmete alusel",
            description = "Lisab useri sisestatud andmete alusel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Mingi viga", content = @Content(schema = @Schema(implementation = ApiError.class)))})

    public void addUser(@RequestBody @Valid UserDetailDto userDetailDto) {
        userService.addUser(userDetailDto);
    }
}



