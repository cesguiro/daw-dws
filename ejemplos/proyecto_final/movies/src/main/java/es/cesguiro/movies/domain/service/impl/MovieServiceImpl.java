package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.controller.DTO.actor.ActorListDTO;
import es.cesguiro.movies.controller.DTO.director.DirectorDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.mapper.ActorMapper;
import es.cesguiro.movies.domain.mapper.DirectorMapper;
import es.cesguiro.movies.domain.mapper.MovieMapper;
import es.cesguiro.movies.persistence.repository.ActorRepository;
import es.cesguiro.movies.persistence.repository.DirectorRepository;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.repository.MovieRepository;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<MovieListDTO> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(null, null)
                .orElseThrow(() -> new ResourceNotFoundException("Empty resource"))
                .stream()
                .map(movieEntity -> MovieMapper.mapper.toModel(movieEntity))
                .map(movie -> MovieMapper.mapper.toListDTO(movie))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDetailDTO find(int id) {
        MovieEntity movieEntity =  movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        Movie movie = MovieMapper.mapper.toModel(movieEntity);
        MovieDetailDTO movieDetailDTO = MovieMapper.mapper.toDetailDTO(movie);

        DirectorEntity directorEntity = directorRepository.findByMovieId(id).orElse(null);
        Director director = DirectorMapper.mapper.toModel(directorEntity);
        DirectorDetailDTO directorDetailDTO = DirectorMapper.mapper.toDetailDTO(director);

        List<ActorListDTO> actorsDTO = actorRepository
                .findByMovieId(id)
                .orElse(null)
                .stream()
                .map(actorEntity -> ActorMapper.mapper.toModel(actorEntity))
                .map(actor -> ActorMapper.mapper.toListDTO(actor))
                .toList();

        movieDetailDTO.setDirector(directorDetailDTO);
        movieDetailDTO.setActors(actorsDTO);

        return movieDetailDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}
