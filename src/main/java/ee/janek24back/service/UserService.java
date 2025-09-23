package ee.janek24back.service;

import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.user.UserMapper;
import ee.janek24back.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserService {

    public static final String REQUEST_PARAM_NAME_USER_ID = "userId";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findUser(Integer userId) {
        UserDto userDto = getValidUserDto(userId);
        return userDto;
    }

    private UserDto getValidUserDto(Integer userId) {
        User user = getValidUser(userId);
        return userMapper.toUserDto(user);
    }

    private User getValidUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException(REQUEST_PARAM_NAME_USER_ID, userId));
    }

    @Transactional
    public void createAndSaveUser(UserDetailDto userDetailDto) {
        User user = createUser(userDetailDto);
        userRepository.save(user);
    }

    private User createUser(UserDetailDto userDetailDto) {
        return userMapper.toUser(userDetailDto);
    }
}