package ee.janek24back.service;

import ee.janek24back.controller.user.UserMapper;
import ee.janek24back.persistence.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserMapper userMapper;


    public String getUserData() {
        return "2000";



    }
}