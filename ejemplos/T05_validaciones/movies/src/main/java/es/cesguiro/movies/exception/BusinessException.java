package es.cesguiro.movies.exception;

public class BusinessException extends RuntimeException{

    private static final String DESCRIPTION = "Business exception";

    public BusinessException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
