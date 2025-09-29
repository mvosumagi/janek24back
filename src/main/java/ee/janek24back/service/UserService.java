package ee.janek24back.service;

import ee.janek24back.controller.user.dto.UserDetailDto;
import ee.janek24back.controller.user.dto.UserDto;
import ee.janek24back.controller.user.dto.UserFullDto;
import ee.janek24back.infrastructure.exception.ForbiddenException;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.address.Address;
import ee.janek24back.persistence.address.AddressMapper;
import ee.janek24back.persistence.address.AddressRepository;
import ee.janek24back.persistence.city.City;
import ee.janek24back.persistence.city.CityRepository;
import ee.janek24back.persistence.company.Company;
import ee.janek24back.persistence.company.CompanyMapper;
import ee.janek24back.persistence.company.CompanyRepository;
import ee.janek24back.persistence.country.Country;
import ee.janek24back.persistence.country.CountryRepository;
import ee.janek24back.persistence.role.Role;
import ee.janek24back.persistence.role.RoleRepository;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.user.UserFullMapper;
import ee.janek24back.persistence.user.UserMapper;
import ee.janek24back.persistence.user.UserRepository;
import ee.janek24back.persistence.usercompany.UserCompany;
import ee.janek24back.persistence.usercompany.UserCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserCompanyRepository userCompanyRepository;
    private final UserFullMapper userFullMapper;

    public UserDto findUser(Integer userId) {
        UserDto userDto = getValidUserDto(userId);
        return userDto;
    }

    @Transactional
    public void addUser(UserDetailDto userDetailDto) {
        if (userRepository.userExistsBy(userDetailDto.getUsername())) {
            throw new ForbiddenException("The username is already taken", 132132);
        }

        User user = createUser(userDetailDto);
        userRepository.save(user);

        Address address = createAddress(userDetailDto, user);
        addressRepository.save(address);

        if (Boolean.TRUE.equals(userDetailDto.getIsCompany())) {
            Company company = getOrCreateCompany(userDetailDto);
            linkUserToCompany(user, company);
        }
    }

    private UserDto getValidUserDto(Integer userId) {
        User user = getValidUser(userId);
        return userMapper.toUserDto(user);
    }

    private User getValidUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
    }

    private User createUser(UserDetailDto userDetailDto) {
        Role role = roleRepository.getRoleCustomer();
        User user = userMapper.toUser(userDetailDto);
        user.setRole(role);
        return user;
    }

    private Address createAddress(UserDetailDto dto, User user) {
        Address address = addressMapper.toAddress(dto);
        address.setUser(user);
        Country country = getValidCountry(dto.getCountryId());
        address.setCountry(country);
        City city = getValidCity(dto.getCityId());
        address.setCity(city);
        address.setType("H");
        return address;
    }

    private Country getValidCountry(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("countryId", countryId));
    }

    private City getValidCity(Integer cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("cityId", cityId));
    }

    private Company getOrCreateCompany(UserDetailDto dto) {
        return companyRepository.findByNumber(dto.getRegNo())
                .map(existing -> {
                    if (dto.getCompanyName() != null && !dto.getCompanyName().isBlank()
                            && !dto.getCompanyName().equals(existing.getName())) {
                        existing.setName(dto.getCompanyName());
                    }
                    return existing;
                })
                .orElseGet(() -> companyRepository.save(companyMapper.toCompany(dto)));
    }

    private void linkUserToCompany(User user, Company company) {
        if (!userCompanyRepository.existsByUserAndCompany(user, company)) {
            UserCompany uc = new UserCompany();
            uc.setUser(user);
            uc.setCompany(company);
            userCompanyRepository.save(uc);
        }
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.userExistsBy(username);
    }

    public UserFullDto getUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("User not found", userId));

        Optional<Address> addressOpt = addressRepository.findTopByUser_IdOrderByIdDesc(userId);
        Optional<UserCompany> ucOpt = userCompanyRepository.findFirstByUser_Id(userId);
        Company company = ucOpt.map(UserCompany::getCompany).orElse(null);

        return userFullMapper.toDto(user, addressOpt.orElse(null), company);
    }
}
