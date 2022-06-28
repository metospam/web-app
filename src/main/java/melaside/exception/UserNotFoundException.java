package melaside.exception;

import lombok.Value;

@Value
public class UserNotFoundException extends RuntimeException {
    Long id;
}
