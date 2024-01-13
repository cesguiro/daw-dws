package es.cesguiro.movies.persistence.dao.db;

import es.cesguiro.movies.persistence.dao.db.exception.DBConnectionException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Datasource {

    private static Datasource datasource;
    private Connection connection;

    @Value("${app.datasource.url}")
    private String urlConnection;
    @Value("${app.datasource.username}")
    private String username;
    @Value("${app.datasource.password}")
    private String password;

    @PostConstruct //Esta anotación sirve para asegurarnos que el método se ejecuta después de que las propiedades hayan sido inyectadas
    private void initilize() {
        try {
            connection = DriverManager.getConnection(
                    urlConnection,
                    username,
                    password
            );
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
        System.out.println("URL: " + urlConnection);
        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }

    public Datasource(){
        /*try {
            connection = DriverManager.getConnection(
                    urlConnection,
                    username,
                    password
            );
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }*/
    }


    private String getParameters (){
        return String.format("url: %s\nUser: %s\nPassword: %s\n",
                urlConnection,
                username,
                password
        );
        /*return String.format("url: %s\nUser: %s\nPassword: %s\n",
                "jdbc:mariadb://localhost:3306/imdb",
                "root",
                "root"
        );*/
    }

    /*public static Datasource getInstance() {
        if(datasource == null) {
            datasource = new Datasource();
        }
        return datasource;
    }*/

    public Connection getConnection() {
        /*try {
            connection = DriverManager.getConnection(
                    urlConnection,
                    username,
                    password
            );
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }*/
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
