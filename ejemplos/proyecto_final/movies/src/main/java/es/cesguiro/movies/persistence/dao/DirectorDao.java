package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.common.dto.DirectorDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DirectorDao {

    Optional<DirectorDto> find(int id);

    DirectorDto save(DirectorDto directorDto);

    void delete(DirectorDto directorDto);
}
