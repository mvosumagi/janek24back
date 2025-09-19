package ee.janek24back.controller.login;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;
    private String roleName;
}