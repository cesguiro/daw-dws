package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.CharacterRepository;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public int create(Movie movie) {
        /*Director director = directorRepository.find(movie.getDirector().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + movie.getDirector().getId()));*/
        /*List<CharacterMovie> characterMovies = movie.getCharacterMovies().stream()
                .map(characterMovie -> characterMovie.setActor(actorRepository.find(characterMovie.getActor().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId()))
                ))
                .toList();*/
        movie.setDirector(directorRepository.find(movie.getDirector().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + movie.getDirector().getId())));

        /*int movieId = movieRepository.insert(movie);
        for (CharacterMovie characterMovie: movie.getCharacterMovies()) {
            characterMovie.setActor(actorRepository.find(characterMovie.getActor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId()))
            );
            characterMovie.setMovieId(movieId);
        }*/
        movie.getCharacterMovies().forEach(characterMovie -> characterMovie.setActor(actorRepository
                .find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId()))));
        //movie.setDirector(director);
        //movie.setCharacterMovies(characterMovies);
        //List<CharacterMovie> characterMovies = new ArrayList<>();

        return movieRepository.insert(movie);
    }

    @Override
    public void update(Movie movie, int directorId, List<Integer> actorIds) {
        movieRepository.find(movie.getId()).orElseThrow(() -> new ResourceNotFoundException("Película no encontrado con id: " + movie.getId()));
        Director director = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId))
                )
                .toList();
        movie.setDirector(director);
        //movie.setActors(actors);
        movieRepository.update(movie);
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        return movieRepository.findByDirectorId(directorId);
    }
}
