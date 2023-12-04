package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
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
        return movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        /*Director director = directorRepository.findByMovieId(id).orElse(null);
        movie.setDirector(director);

        List<Actor> actors = actorRepository.findByMovieId(id);

        movie.setActors(actors);*/

    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public Movie create(Movie movie) {
        movie.setDirector(directorRepository.find(movie.getDirector().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + movie.getDirector().getId())));
        movie.getCharacterMovies().forEach(characterMovie -> characterMovie.setActor(actorRepository
                .find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId()))));
        return movieRepository.insert(movie);
    }

    @Override
    public Movie update(Movie movie) {
        Movie existingMovie = movieRepository.find(movie.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrado con id: " + movie.getId()));
        MovieMapper.mapper.updateMovieFromMovieUpdate(movie, existingMovie);
        return movieRepository.update(existingMovie);
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        return movieRepository.findByDirectorId(directorId);
    }

    @Override
    public void delete(int id) {
        Movie movie = movieRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrado con id: " + id));
        movieRepository.delete(movie);
    }
}
