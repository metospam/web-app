package melaside.exception;

import lombok.Value;

@Value
public class GenreNotFoundException extends RuntimeException{
    Long id;
}
