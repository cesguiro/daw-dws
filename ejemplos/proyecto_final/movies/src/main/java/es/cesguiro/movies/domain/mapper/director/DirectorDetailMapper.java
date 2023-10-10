package es.cesguiro.movies.domain.mapper.director;

import es.cesguiro.movies.controller.DTO.director.DirectorDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;

public class DirectorDetailMapper {

    public DirectorDetailDTO toDTO(Director director) {
        return new DirectorDetailDTO(
                director.getId(),
                director.getName(),
                director.getBirthYear(),
                director.getDeathYear()
        );
    }

    public Director fromEntity(DirectorEntity directorEntity) {
        return new Director(
                directorEntity.getId(),
                directorEntity.getName(),
                directorEntity.getBirthYear(),
                directorEntity.getDeathYear()
        );
    }
}
