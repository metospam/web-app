package melaside.model.dto;

import lombok.Data;
import melaside.model.Author;
import melaside.validator.AuthorNotEmpty;

import javax.validation.constraints.NotBlank;

@Data
@AuthorNotEmpty
public class BookDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Long authorId;

    private String authorInitials;

}
