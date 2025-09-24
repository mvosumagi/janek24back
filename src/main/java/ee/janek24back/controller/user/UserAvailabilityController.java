package ee.janek24back.controller.user;

import ee.janek24back.controller.user.dto.UsernameAvailabilityResponse;
import ee.janek24back.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserAvailabilityController {
    private final UserRepository userRepository;

    @GetMapping("/check-username")
    public UsernameAvailabilityResponse check(@RequestParam String username) {
        boolean taken = userRepository.userExistsBy(username);
        return new UsernameAvailabilityResponse(!taken);
    }
}
