package ee.janek24back.controller;

import ee.janek24back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    @Operation(
            summary = "Returns a simple value",
            description = "Demo endpoint that calls UserService and returns a string."
    )

    public String getUserValue() {
        // call the service and return the result to the client
        return userService.userService(); // -> "2000"
    }

}



