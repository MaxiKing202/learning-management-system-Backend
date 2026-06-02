package dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    Long id;

    String email;

    String matrikelNumber;

}
