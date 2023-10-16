package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.actor.ActorListDTO;
import es.cesguiro.movies.dto.director.DirectorDetailDTO;
import es.cesguiro.movies.dto.director.DirectorListDTO;
import es.cesguiro.movies.dto.mapper.ActorMapper;
import es.cesguiro.movies.dto.mapper.DirectorMapper;
import es.cesguiro.movies.dto.mapper.MovieMapper;
import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.ActorRepository;
import es.cesguiro.movies.persistence.DirectorRepository;
import es.cesguiro.movies.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private String applicationURL;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<MovieListDTO> getAll(Integer page, Integer pageSize) {
        List<MovieListDTO> movieListDTOs = movieRepository.getAll(page, pageSize);
        movieListDTOs.forEach(movieListDTO -> movieListDTO.setLink(applicationURL + "/movies/" + movieListDTO.getId()));
        return movieListDTOs;
    }

    @Override
    public List<MovieListDTO> getAll() {
        List<MovieListDTO> movieListDTOs = movieRepository.getAll(null, null);/*.stream()
                .map(movie -> MovieMapper.mapper.toListDTO(movie))
                .toList();*/
        movieListDTOs.forEach(movieListDTO -> movieListDTO.setLink(applicationURL + "/movies/" + movieListDTO.getId()));
        return movieListDTOs;
    }


    @Override
    public MovieDetailDTO find(int id) {
        MovieDetailDTO movieDetailDTO = movieRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        //MovieDetailDTO movieDetailDTO = MovieMapper.mapper.toDetailDTO(movie);

        Director director = directorRepository.findByMovieId(id).orElse(null);
        DirectorListDTO directorListDTO = DirectorMapper.mapper.toListDTO(director);
        directorListDTO.setLink(applicationURL + "/directors/" + directorListDTO.getId());
        movieDetailDTO.setDirector(directorListDTO);

        List<ActorListDTO> actorsListDTO = actorRepository.findByMovieId(id).stream()
                .map(actor -> ActorMapper.mapper.toListDTO(actor))
                .toList();

        actorsListDTO.forEach(actorListDTO -> actorListDTO.setLink(applicationURL + "/actors/" + actorListDTO.getId()));
        movieDetailDTO.setActors(actorsListDTO);

        return movieDetailDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}
