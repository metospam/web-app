package melaside.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderDto {

    @NotBlank
    private String initials;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;
}
