package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.common.exception.BadRequestException;
import es.cesguiro.movies.common.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.mapper.CharacterMovieDomainMapper;
import es.cesguiro.movies.domain.mapper.MovieDomainMapper;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.domain.repository.CharacterMovieRepository;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final CharacterMovieRepository characterMovieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository, CharacterMovieRepository characterMovieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.characterMovieRepository = characterMovieRepository;
        this.actorRepository = actorRepository;
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
                        this.findMovie(id)
                );
    }

    private Movie findMovie(int id) {
        return movieRepository
                .find(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Movie not found with id: " + id)
                );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public MovieDto create(MovieDto movieDTO) {
        Movie movie= MovieDomainMapper.mapper.toMovie(movieDTO);
        this.addDirectorDto(movie, movieDTO.getDirectorDto());

        List<CharacterMovieDto> characterMovieDtoList = movieDTO.getCharacterMovieDtoList();
        List<CharacterMovie> characterMovieList = CharacterMovieDomainMapper.mapper.toCharacterMovieList(characterMovieDtoList);
        characterMovieList.forEach(
                characterMovie -> {
                    characterMovie
                            .setActor(
                                    actorRepository
                                            .find(
                                                    characterMovie.getActor().getId())
                                            .orElseThrow(
                                                    () -> new ResourceNotFoundException("Actor not found with id: " + characterMovie.getActor().getId())
                                            )
                    );
                }
        );
        movie.setCharacterMovieList(characterMovieList);


        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository
                                .save(movie)
                );
    }

    private void addDirectorDto(Movie movie, DirectorDto directorDto) {
        if(directorDto == null || directorDto.getId() == null) {
            throw new BadRequestException("Director cannot be null");
        }
        Director director = directorRepository
                .find(directorDto.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Director not found with id: " + directorDto.getId())
                );
        movie.setDirector(director);
    }
    @Override
    public MovieDto update(MovieDto movieDTO) {
        if(movieDTO.getId() == null) {
            throw new BadRequestException("Movie cannot be null");
        }
        Movie movie = this.findMovie(movieDTO.getId());
        this.addDirectorDto(movie, movieDTO.getDirectorDto());

        MovieDomainMapper.mapper.updataMovieFromMovieDto(movieDTO, movie);
        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository.save(movie)
                );
    }

    @Override
    public Stream<MovieDto> findByDirectorId(int directorId) {
        return movieRepository
                .findByDirectorId(directorId)
                .map(MovieDomainMapper.mapper::toMovieDto);
    }

    @Override
    public void delete(int id) {
        Movie movie = this.findMovie(id);
        movieRepository.delete(movie);
    }

    @Override
    public MovieDto addCharacterMovie(int id, CharacterMovieDto characterMovieDto) {
        Movie movie = this.findMovie(id);
        CharacterMovie characterMovie = this.buildCharacterMovie(characterMovieDto);
        movie.addCharacterMovie(characterMovie);
        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository.save(movie)
                );
    }

    @Override
    public MovieDto updateCharacterMovie(int id, CharacterMovieDto characterMovieDto) {
        Movie movie = this.findMovie(id);
        CharacterMovie characterMovie = this.buildCharacterMovie(characterMovieDto);
        movie.updateCharacterMovie(characterMovie);
        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository.save(movie)
                );
    }

    private CharacterMovie buildCharacterMovie(CharacterMovieDto characterMovieDto) {
        CharacterMovie characterMovie = CharacterMovieDomainMapper
                .mapper
                .toCharacterMovie(characterMovieDto);
        Actor actor = actorRepository.find(
                        characterMovie
                                .getActor()
                                .getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Actor not found with id: " + characterMovieDto.getActorDto().getId())
                );
        characterMovie.setActor(actor);
        return characterMovie;
    }

    @Override
    public MovieDto deleteCharacterMovie(int movieId, int characterMovieId) {
        Movie movie = this.findMovie(movieId);
        movie.deleteCharacterMovie(characterMovieId);
        return MovieDomainMapper
                .mapper
                .toMovieDto(
                        movieRepository.save(movie)
                );
    }
}
