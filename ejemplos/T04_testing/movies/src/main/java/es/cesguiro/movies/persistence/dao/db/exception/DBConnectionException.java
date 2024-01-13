package es.cesguiro.movies.persistence.dao.db.exception;

import java.util.Objects;

public class DBConnectionException extends RuntimeException{

    private static final String DESCRIPTION = "Can't establish connection";


    public DBConnectionException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
