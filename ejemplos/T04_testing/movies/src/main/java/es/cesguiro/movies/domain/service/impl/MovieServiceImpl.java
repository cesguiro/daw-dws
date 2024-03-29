package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size) {
        return movieRepository.getAll(page, page_size);
    }

    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id);

        return movie;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}
