package es.cesguiro.movies.common.exception;

public class DtoValidationException extends RuntimeException{
    private static final String DESCRIPTION = "Validation error";

    public DtoValidationException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}

