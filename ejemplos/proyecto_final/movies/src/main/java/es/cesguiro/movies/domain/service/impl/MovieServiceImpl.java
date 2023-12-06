package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.common.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.mapper.MovieDomainMapper;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    /*@Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;*/

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Stream<MovieDto> getAll(Integer page, Integer pageSize) {
        return movieRepository
                .getAll(page, pageSize)
                .map(MovieDomainMapper.mapper::toMovieDto);
    }

    @Override
    public Stream<MovieDto> getAll() {
        return  movieRepository.getAll(null, null)
                .map(MovieDomainMapper.mapper::toMovieDto);
    }


    @Override
    public MovieDto find(int id) {
        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository
                                .find(id)
                                .orElseThrow(
                                        () -> new ResourceNotFoundException("Movie not found with id: " + id)
                                )
                );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public MovieDto create(MovieDto movieDTO) {
        /*movie.setDirector(directorRepository.find(movie.getDirector().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + movie.getDirector().getId())));
        movie.getCharacterMovies().forEach(characterMovie -> characterMovie.setActor(actorRepository
                .find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId()))));
        return movieRepository.insert(movie);*/
        return null;
    }

    @Override
    public MovieDto update(MovieDto movieDTO) {
        /*Movie existingMovie = this.find(movie.getId());
        MovieMapper.mapper.updateMovieFromMovieUpdate(movie, existingMovie);
        return movieRepository.update(existingMovie);*/
        return null;
    }

    @Override
    public Stream<MovieDto> findByDirectorId(int directorId) {
        /*return movieRepository.findByDirectorId(directorId);*/
        return null;
    }

    @Override
    public void delete(int id) {
        /*Movie movie = this.find(id);
        movieRepository.delete(movie);*/
    }

    @Override
    public MovieDto addCharacterMovie(int id, CharacterMovieDto characterMovieDTO) {
        /*Movie movie = this.find(id);
        Actor actor = actorRepository.find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + characterMovie.getActor().getId()));
        characterMovie.setActor(actor);
        movie.addCharacterMovie(characterMovie); //por si hay alguna validación
        return movieRepository.update(movie);*/
        return null;
    }

    @Override
    public MovieDto updateCharacterMovie(int id, CharacterMovieDto characterMovieDTO) {
        /*Movie movie = this.find(id);
        Actor actor = actorRepository.find(characterMovie.getActor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + characterMovie.getActor().getId()));
        characterMovie.setActor(actor);
        movie.updateCharacterMovie(characterMovie); //por si hay alguna validación
        return movieRepository.update(movie);*/
        return null;
    }

    @Override
    public void deleteCharacterMovie(int movieId, int characterMovieId) {
    }
}
