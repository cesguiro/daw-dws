package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieDAO movieDAO;

    @Autowired
    public MovieRepositoryImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }
    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        List<MovieEntity> movieEntities;
        //return movieDAO.findAll().stream().map(MovieMapper.mapper::toMovie).toList();
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            movieEntities = movieDAO.findAll(pageable).stream().toList();
        } else {
            movieEntities = movieDAO.findAll();

        }
        //return MovieMapper.mapper.toMovieList(movieDAO.findAll());
        return MovieMapper.mapper.toMovieList(movieEntities);
    }

    @Override
    public Optional<Movie> find(int id) {
            //MovieEntity movieEntity = movieDAO.findById(id).get();
            /*if(movieEntity != null) {
                movieEntity.getDirectorEntity();
                System.out.println(movieEntity.getDirectorEntity().getName());
                System.out.println(movieEntity.getDirectorEntity().getBirthYear());
                movieEntity.getActorEntities();
                System.out.println(movieEntity.getActorEntities().get(1).getName());
                //movieEntity.getCharacterEntities(connection, characterMovieDAO).forEach(CharacterMovieEntity -> CharacterMovieEntity.getActorEntity(connection, actorDAO));
                //movieEntity.getCharacterEntities().forEach(CharacterEntity -> CharacterEntity.getActor(connection, actorDAO));
            }*/
            return Optional.ofNullable(MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieDAO.findById(id).get()));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieDAO.count();
    }

    @Override
    public int insert(Movie movie) {
        return 0;
    }

    @Override
    public void update(Movie movie) {

    }
}
