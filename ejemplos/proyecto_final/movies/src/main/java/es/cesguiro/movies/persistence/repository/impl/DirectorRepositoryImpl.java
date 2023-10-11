package es.cesguiro.movies.persistence.repository.impl;

import es.cesguiro.movies.persistence.repository.DirectorRepository;
import es.cesguiro.movies.persistence.DAO.DirectorDAO;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public Optional<DirectorEntity> findByMovieId(int movieId) {
        return directorDAO.findByMovieId(movieId);
    }

    /*@Override
    public int insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        Connection connection = DBUtil.open();
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
    }

    @Override
    public Director find(int id) {
        return null;
    }

    @Override
    public void update(Director director) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            List<Object> params = new ArrayList<>();
            params.add(director.getName());
            params.add(director.getBirthYear());
            params.add(director.getDeathYear());
            params.add(director.getId());
            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }*/
}
