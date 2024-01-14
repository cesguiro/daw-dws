package es.cesguiro.movies.persistence.dao.db;

import es.cesguiro.movies.persistence.dao.db.exception.DBConnectionException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    @Getter
    private Connection connection;

    @Value("${spring.datasource.url}")
    private String urlConnection;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    //Si queremos hacer Singleton
    /*private DBConnection(){

    }*/

    @PostConstruct //Esta anotación sirve para asegurarnos que el método se ejecuta después de que las propiedades hayan sido inyectadas
    private void initilize() {
        System.out.println("Estableciendo conexión....");
        try {
            connection = DriverManager.getConnection(
                    urlConnection,
                    username,
                    password
            );
            System.out.println("Conexión establecida con la bbdd con los parámetros: ");
            System.out.println(this.getParameters());
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
    }


    private String getParameters (){
        return String.format("url: %s\nUser: %s\nPassword: %s\n",
                urlConnection,
                username,
                password
        );
    }

}
