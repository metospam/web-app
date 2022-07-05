package melaside.validator;

import melaside.model.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        boolean valid = userDto.getPassword().equals(userDto.getMatchingPassword());

        if(!valid){
            context.disableDefaultConstraintViolation();
            context

                    .buildConstraintViolationWithTemplate("Пароли должны совпадать.")
                    .addPropertyNode("password")
                    .addConstraintViolation()

                    .buildConstraintViolationWithTemplate("Пароли должны совпадать.")
                    .addPropertyNode("matchingPassword")
                    .addConstraintViolation();
        }

        return valid;
    }
}