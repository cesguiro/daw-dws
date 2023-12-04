package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
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
        Movie existingMovie = this.find(movie.getId());
        MovieMapper.mapper.updateMovieFromMovieUpdate(movie, existingMovie);
        return movieRepository.update(existingMovie);
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        return movieRepository.findByDirectorId(directorId);
    }

    @Override
    public void delete(int id) {
        Movie movie = this.find(id);
        movieRepository.delete(movie);
    }

    @Override
    public Movie addCharacterMovie(int id, CharacterMovie characterMovie) {
        Movie movie = this.find(id);
        Actor actor = actorRepository.find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + characterMovie.getActor().getId()));
        characterMovie.setActor(actor);
        movie.addCharacterMovie(characterMovie); //por si hay alguna validación
        return movieRepository.update(movie);
    }

    @Override
    public Movie updateCharacterMovie(int id, CharacterMovie characterMovie) {
        Movie movie = this.find(id);
        Actor actor = actorRepository.find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + characterMovie.getActor().getId()));
        characterMovie.setActor(actor);
        movie.updateCharacterMovie(characterMovie); //por si hay alguna validación
        return movieRepository.update(movie);
    }
}
