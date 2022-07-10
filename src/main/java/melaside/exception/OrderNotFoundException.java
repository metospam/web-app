package melaside.exception;

import lombok.Value;

@Value
public class OrderNotFoundException extends RuntimeException {
    Long id;
}
