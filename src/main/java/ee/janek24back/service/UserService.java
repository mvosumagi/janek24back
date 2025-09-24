package ee.janek24back.service;

import ee.janek24back.controller.address.AddressDto;
import ee.janek24back.controller.address.dto.AddressMapper;
import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.infrastructure.exception.ForbiddenException;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.address.Address;
import ee.janek24back.persistence.address.AddressRepository;
import ee.janek24back.persistence.country.Country;
import ee.janek24back.persistence.country.CountryRepository;
import ee.janek24back.persistence.role.Role;
import ee.janek24back.persistence.role.RoleRepository;
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
    private final AddressMapper addressMapper;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final AddressRepository addressRepository;

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
    public void addUser(UserDetailDto userDetailDto) {
        if (userRepository.userExistsBy(userDetailDto.getUsername())) {
            throw new ForbiddenException("The username is already taken", 132132);
        }

        User user = createUser(userDetailDto);
        userRepository.save(user);


       // return user.getId();


        // todo: Address rea lisamine andmbeaasi
        // todo: leia vajalikud foregin key objewktis
        // todo: mapperiga lood uuse addreess objekti, saad infi userDetailDtost
        // todo: salvestad

        // todo: Kui on company, lisad company andmed andmebaasi
    }

    private User createUser(UserDetailDto userDetailDto) {
        Role role = roleRepository.getRoleCustomer();
        User user = userMapper.toUser(userDetailDto);
        user.setRole(role);
        return user;
    }

}