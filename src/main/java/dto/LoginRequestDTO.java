package dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

}
