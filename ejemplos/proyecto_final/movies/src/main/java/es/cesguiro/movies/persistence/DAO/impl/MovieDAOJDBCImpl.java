package es.cesguiro.movies.persistence.DAO.impl;

import es.cesguiro.movies.persistence.DAO.MovieDAO;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import es.cesguiro.movies.persistence.factory.EntityFactory;
import es.cesguiro.movies.persistence.factory.impl.MovieFactory;
import es.cesguiro.movies.persistence.mapper.impl.MovieMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieDAOJDBCImpl extends GenericDAOJDBCImpl<MovieEntity, Integer> implements MovieDAO{

    public MovieDAOJDBCImpl() {
        super(new MovieMapper(), new MovieFactory());
    }
    /*public Optional<List<MovieEntity>> findAll() {
        System.out.println("Table Name = " + this.tableName);
        return super.findAll();
    }*/
    /*@Override
    public Optional<List<MovieListDTO>> paginate(Integer page, Integer pageSize) {
        return this.findAll();
    }

    @Override
    public Optional<MovieDetailDTO> findById(int id) {
        MovieDetailDTO movieDetailDTO;
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return Optional.of(
                        new MovieDetailDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getInt("runtime")
                        )
                );
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }*/
}
