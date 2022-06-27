package melaside.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}
