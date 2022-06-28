package melaside.exception;

import lombok.Value;

@Value
public class AuthorNotFoundException extends RuntimeException {
    Long id;
}
