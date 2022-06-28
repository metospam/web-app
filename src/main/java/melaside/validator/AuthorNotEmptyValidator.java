package melaside.validator;

import melaside.model.dto.BookDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorNotEmptyValidator implements ConstraintValidator<AuthorNotEmpty, BookDto> {

    @Override
    public void initialize(AuthorNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BookDto bookDto, ConstraintValidatorContext context) {

        boolean valid = bookDto.getAuthorId() != -1 ||
                (bookDto.getAuthorInitials() != null && bookDto.getAuthorInitials().length() > 0);

        if(!valid){
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("You must indicate the author or create new.")
                    .addPropertyNode("authorId")
                    .addConstraintViolation()

                    .buildConstraintViolationWithTemplate("You must indicate author or create new.")
                    .addPropertyNode("authorInitials")
                    .addConstraintViolation();
        }

        return valid;
    }
}
