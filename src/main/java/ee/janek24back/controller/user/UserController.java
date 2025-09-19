package ee.janek24back.controller.user;

import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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








}



