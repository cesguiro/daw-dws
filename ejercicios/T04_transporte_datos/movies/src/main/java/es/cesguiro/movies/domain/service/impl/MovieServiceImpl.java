package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.dto.ActorDTO;
import es.cesguiro.movies.dto.DirectorDTO;
import es.cesguiro.movies.dto.MovieDTO;
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
    public List<MovieDTO> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);

        /*Si necesitáramos crear entidades Movie (por si hay campos calculados, por ejemplo):
        /*List<MovieDTO> movieDTOs = movieRepository.getAll(page, pageSize);
        List<Movie> movies = movieDTOs.stream()
                .map(MovieMapper.mapper::toMovie)
                .toList();
        return movies.stream()
                .map(MovieMapper.mapper::toMovieDTO)
                .toList();
         */
    }

    @Override
    public List<MovieDTO> getAll() {
        return  movieRepository.getAll(null, null);
    }


    @Override
    public MovieDTO find(int id) {
        MovieDTO movieDTO = movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        DirectorDTO directorDTO = directorRepository.findByMovieId(id).orElse(null);
        movieDTO.setDirectorDTO(directorDTO);

        List<ActorDTO> actorDTOs = actorRepository.findByMovieId(id);

        movieDTO.setActorDTOs(actorDTOs);

        return movieDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(Movie movie, int directorId, List<Integer> actorIds) {
        Director director = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId))
                )
                .toList();
        movie.setDirector(director);
        movie.setActors(actors);
        return movieRepository.insert(movie);
    }
}
