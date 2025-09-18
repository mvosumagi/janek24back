package ee.janek24back.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final Loginservice loginService;

    @GetMapping ("/login")
    public void login(RequestParam String username, @RequestParam String password) {
        loginservice.login(username , password);
    }
}
