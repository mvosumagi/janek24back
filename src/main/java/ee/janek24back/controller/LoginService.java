package ee.janek24back.controller;

import ee.janek24back.Status;
import ee.janek24back.controller.user.UserMapper;
import ee.janek24back.infrastructure.exception.ForbiddenException;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ee.janek24back.Error.INCORRECT_CREDENTIALS;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse login(String username, String password) {
        User user = getValidUser(username, password);
        return userMapper.toLoginResponse(user);
    }
        private User getValidUser (String username, String password){
            return userRepository.findUserBy(username, password, Status.ACTIVE.getCode())
                    .orElseThrow(() -> new ForbiddenException(INCORRECT_CREDENTIALS.getMessage(), INCORRECT_CREDENTIALS.getErrorCode()));
    }
}