package ee.janek24back.controller.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class UserDetailDto extends UserDto {

    @NotNull
    private Boolean isCompany;

    @Size(max = 255)
    private String companyName;

    @Size(max = 100)
    private String regNo;

}
