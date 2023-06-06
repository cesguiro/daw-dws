package es.cesguiro.movies.business.service.impl;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.business.service.MovieService;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.impl.MovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    //@Autowired
    //private MovieRepository movieRepository;
    private MovieRepository movieRepository = new MovieRepositoryImpl();
    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @Override
    public Movie find(String id) {
        Movie movie = movieRepository.find(id);

        return movie;
    }
}
