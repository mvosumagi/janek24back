package ee.janek24back.controller.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ee.janek24back.persistence.user.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Value
public class UserDto implements Serializable {
    Integer id;
    Integer roleId;
    String roleName;
    @NotNull
    @Size(max = 60)
    String username;
    @NotNull
    @Size(max = 60)
    String password;
    @NotNull
    @Size(max = 1)
    String status;
}