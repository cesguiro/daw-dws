package es.cesguiro.movies.business.service.impl;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.business.service.MovieService;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.impl.MovieRepositoryImpl;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository = new MovieRepositoryImpl();
    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }
}
