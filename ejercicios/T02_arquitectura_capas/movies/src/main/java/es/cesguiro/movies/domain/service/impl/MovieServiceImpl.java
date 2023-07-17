package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.impl.MovieRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Named(Value="") (javax.inject.named)
public class MovieServiceImpl implements MovieService {

    //@Inject (javax.inject)
    //private MovieRepository movieRepository;
    private MovieRepository movieRepository = new MovieRepositoryImpl();

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id);

        return movie;
    }
}
