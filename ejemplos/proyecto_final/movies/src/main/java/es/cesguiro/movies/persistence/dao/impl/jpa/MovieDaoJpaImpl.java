package es.cesguiro.movies.persistence.dao.impl.jpa;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.persistence.dao.MovieDao;
import es.cesguiro.movies.persistence.dao.impl.jpa.mapper.MovieJpaMapper;
import es.cesguiro.movies.persistence.dao.impl.jpa.repository.MovieJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class MovieDaoJpaImpl implements MovieDao {

    MovieJpaRepository movieJpaRepository;

    @Autowired
    public MovieDaoJpaImpl(MovieJpaRepository movieJpaRepository) {
        this.movieJpaRepository = movieJpaRepository;
    }

    @Override
    public Stream<MovieDto> getAll(Integer page, Integer pageSize) {
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            return movieJpaRepository
                    .findAll(pageable)
                    .stream()
                    .map(MovieJpaMapper.mapper::toMovieDto);
        }
        return movieJpaRepository
                .findAll()
                .stream()
                .map(MovieJpaMapper.mapper::toMovieDto);
    }

    @Override
    public Optional<MovieDto> find(int id) {
        return Optional.ofNullable(
                MovieJpaMapper
                        .mapper
                        .toMovieDTOWithDirectorAndCharacterMovies(
                                movieJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public long count() {
        return movieJpaRepository.count();
    }
}
