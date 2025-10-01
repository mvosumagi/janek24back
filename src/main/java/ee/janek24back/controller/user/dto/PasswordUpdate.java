package ee.janek24back.controller.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdate {

    @NotNull
    private Integer userId;

    @NotNull
    private String newPassword;
}