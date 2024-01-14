package es.cesguiro.movies.persistence.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class DBUtil {

    private final Datasource datasource;



    @Autowired
    public DBUtil(Datasource datasource) {
       this.datasource = datasource;
   }

    public ResultSet select(String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(sql, values);
            return preparedStatement.executeQuery();            
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql);
        }
    }

    public int insert(String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(sql, values);
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


    public int update(String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(sql, values);
            int numRows = preparedStatement.executeUpdate();
            return numRows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement setParameters(String sql, List<Object> values){
        Connection connection = datasource.getConnection();
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

    public int delete(String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
