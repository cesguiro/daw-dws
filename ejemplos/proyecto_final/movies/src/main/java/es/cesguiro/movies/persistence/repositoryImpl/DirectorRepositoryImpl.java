package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;
    @Override
    public int insert(Director director) {
        return 0;
    }

    @Override
    public Optional<Director> find(int id) {
        DirectorEntity directorEntity = directorDAO.findById(id).orElse(null);
        if(directorEntity == null) {
            return Optional.empty();
        }
        //return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
        return Optional.of(DirectorMapper.mapper.toDirector(directorEntity));
    }

    @Override
    public void update(Director director) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Director> findByMovieId(int movieId) {
        return Optional.empty();
    }
}
