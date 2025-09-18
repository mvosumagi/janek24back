package ee.janek24back.controller;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class LoginService {

    public LoginResponse login(String username, String password) {
        User user = getValidUser(username, password);
        return userMapper.toLoginResponse(user);
    }
}