package dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

}
