package es.cesguiro.movies.db;

import es.cesguiro.movies.db.exception.DBConnectionException;

import java.sql.*;
import java.util.List;

public class DBUtil {

    private static final String DRIVER = "mariadb";
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "imdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public static Connection open(){
        try {
            String url = "jdbc:" + DRIVER + "://" + HOST + ":" + PORT + "/" + DATABASE;
            Connection connection = DriverManager.getConnection(
                url,
                USER,
                PASSWORD
            );
            return connection;
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
    }

    private static String getParameters (){
        String url = "jdbc:" + DRIVER + "://" + HOST + ":" + PORT + "/" + DATABASE;

        return String.format("Driver: %s\nHost: %s\nPort: %d\nDatabase:%s\nUser: %s\nPassword: %s\n",
                DRIVER,
                HOST,
                PORT,
                DATABASE,
                USER,
                PASSWORD
        ) + "URL: " + url;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBConnectionException("Can't close connection");
        }
    }

    public static ResultSet select(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeQuery();            
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql);
        }
    }

    public static int insert(Connection connection, String sql, List<Object> values) {    
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("No se puede leer el último id generado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int update(Connection connection, String sql, List<Object> values) {    
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            int numRows = preparedStatement.executeUpdate();
            return numRows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement setParameters(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(values != null) {
                for(int i=0;i<values.size();i++) {
                    Object value = values.get(i);
                    preparedStatement.setObject(i+1,value);
                }
            }    
            return preparedStatement;                        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
