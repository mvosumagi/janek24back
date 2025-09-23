package ee.janek24back.controller.user.dto;

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
    private Integer id;

    @NotNull
    private Integer roleId;

    @NotNull
    private String roleName;

    @NotNull
    @Size(max = 60)
    private String username;

    @NotNull
    @Size(max = 60)
    private String password;

    @NotNull
    @Size(max = 1)
    private String status;

    }