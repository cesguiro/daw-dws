package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<Movie> getAll() {
        return  movieRepository.getAll(null, null);
    }


    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        Director director = directorRepository.findByMovieId(id).orElse(null);
        movie.setDirector(director);

        List<Actor> actors = actorRepository.findByMovieId(id);

        movie.setActors(actors);

        return movie;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}
