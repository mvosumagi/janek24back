package ee.janek24back.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public LoginResponse login(@RequestParam String username, @RequestParam String password) {
        @Operation(summary = "Sisse logimine. Tagastab userId ja roleName",
                description = """
                        Süsteemist otsitakse username ja password abil kasutajat, kelle konto on ka aktiivne.
                        Kui vastet ei leita vistakse viga errorCode'ga 111""")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "403", description = "Vale kasutajanimi või parool", content = @Content(schema = @Schema(implementation = ApiError.class)))})


        return loginService.login(username, password);


    }
}
