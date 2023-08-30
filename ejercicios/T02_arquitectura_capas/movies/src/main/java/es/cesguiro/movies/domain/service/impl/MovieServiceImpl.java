package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.impl.MovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository = new MovieRepositoryImpl();

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id);
        if(movie == null) {
            throw new ResourceNotFoundException("Movie not found with id: " + id);
        }
        return movie;
    }
}
