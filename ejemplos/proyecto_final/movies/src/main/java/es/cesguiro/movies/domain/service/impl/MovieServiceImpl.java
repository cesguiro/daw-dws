package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.controller.DTO.actor.ActorDetailDTO;
import es.cesguiro.movies.controller.DTO.actor.ActorListDTO;
import es.cesguiro.movies.controller.DTO.director.DirectorDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.mapper.GeneralMapper;
import es.cesguiro.movies.domain.mapper.MovieMapper;
import es.cesguiro.movies.domain.mapper.actor.ActorDetailMapper;
import es.cesguiro.movies.domain.mapper.actor.ActorListMapper;
import es.cesguiro.movies.domain.mapper.director.DirectorDetailMapper;
import es.cesguiro.movies.domain.mapper.movie.MovieDetailMapper;
import es.cesguiro.movies.domain.mapper.movie.MovieListMapper;
import es.cesguiro.movies.persistence.entity.impl.ActorEntity;
import es.cesguiro.movies.persistence.repository.ActorRepository;
import es.cesguiro.movies.persistence.repository.DirectorRepository;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.repository.MovieRepository;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    private GeneralMapper generalMapper;

    @Override
    public List<MovieListDTO> getAll(Integer page, Integer pageSize) {
        //MovieListMapper movieListMapper = new MovieListMapper();
        List<MovieEntity> movieEntities = movieRepository.getAll(null, null).orElseThrow(() -> new ResourceNotFoundException("Empty resource"));
        List<Movie> movies = generalMapper.MovieEntitiesToMovies(movieEntities);
        //List<Movie> movies = movieEntities.stream().map(movie -> MovieMapper.mapper.fromEntityToModel(movie)).collect(Collectors.toList());
        /*return movieEntities
                .stream()
                .map(movieEntity -> MovieMapper.mapper.fromEntityToModel(movieEntity))
                .map(movie -> MovieMapper.mapper.fromModelToDTO(movie))
                .collect(Collectors.toList());*/
        return generalMapper.MoviesToMovieListDTO(movies);

        /*List<MovieEntity> movieEntities = movieRepository.getAll(null, null).orElseThrow(() -> new ResourceNotFoundException("Empty resource"));
        List<Movie> movies = movieListMapper.fromListEntity(movieEntities);*/
        //return movieListMapper.ToListDTO(movies);
    }

    @Override
    public MovieDetailDTO find(int id) {

        MovieDetailMapper movieDetailMapper = new MovieDetailMapper();
        DirectorDetailMapper directorDetailMapper = new DirectorDetailMapper();
        ActorListMapper actorListMapper = new ActorListMapper();

        MovieEntity movieEntity =  movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        Movie movie = movieDetailMapper.fromEntity(movieEntity);
        MovieDetailDTO movieDetailDTO = movieDetailMapper.toDTO(movie);

        DirectorEntity directorEntity = directorRepository.findByMovieId(id).orElse(null);
        Director director = directorDetailMapper.fromEntity(directorEntity);
        DirectorDetailDTO directorDetailDTO = directorDetailMapper.toDTO(director);

        List<ActorEntity> actorEntities = actorRepository.findByMovieId(id).orElse(null);
        List<Actor> actors = actorListMapper.fromListEntity(actorEntities);
        List<ActorListDTO> actorsDTO = actorListMapper.ToListDTO(actors);

        movieDetailDTO.setDirector(directorDetailDTO);
        movieDetailDTO.setActors(actorsDTO);

        return movieDetailDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}
