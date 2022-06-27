package melaside.model.dto;

import melaside.validator.PasswordMatches;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Transient
    @NotBlank
    private String matchingPassword;
}
