package ee.janek24back.controller.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDetailDto extends UserDto {

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
    private String phone;

    @NotNull
    private Boolean isCompany;

    @Size(max = 255)
    private String companyName;

    @Size(max = 100)
    private String regNo;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 255)
    private String address;
}
