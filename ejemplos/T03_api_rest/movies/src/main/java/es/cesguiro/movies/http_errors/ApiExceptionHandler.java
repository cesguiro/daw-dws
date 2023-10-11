package es.cesguiro.movies.http_errors;

import es.cesguiro.movies.exception.DBConnectionException;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.exception.SQLStatmentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
        return new ErrorMessage("Invalid request body", HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class,
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
