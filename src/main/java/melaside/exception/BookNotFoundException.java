package melaside.exception;

import lombok.Value;

@Value
public class BookNotFoundException extends RuntimeException {
    Long id;
}
