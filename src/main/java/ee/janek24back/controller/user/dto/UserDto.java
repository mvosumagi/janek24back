package ee.janek24back.controller.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.janek24back.persistence.user.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @NotNull
    @Size(max = 60)
    private String username;

    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 50)
    private String phoneNumber;

    @NotNull
    private Integer countryId;

    @NotNull
    private Integer cityId;

    @Size(max = 100)
    private String state;

    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 60)
    private String password;

    @NotNull
    @Size(max = 20)
    private String postalCode;



    }