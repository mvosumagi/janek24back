package ee.janek24back.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdate {
    private Integer userId;
    private String oldPassword;
    private String newPassword;
}
