package es.cesguiro.movies.persistence.dao.impl.jpa;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.persistence.dao.DirectorDao;
import es.cesguiro.movies.persistence.dao.impl.jpa.mapper.DirectorJpaMapper;
import es.cesguiro.movies.persistence.dao.impl.jpa.repository.DirectorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DirectorDaoJpaImpl implements DirectorDao {

    final DirectorJpaRepository directorJpaRepository;

    @Autowired
    public DirectorDaoJpaImpl(DirectorJpaRepository directorJpaRepository) {
        this.directorJpaRepository = directorJpaRepository;
    }

    @Override
    public Optional<DirectorDto> find(int id) {
        return Optional.ofNullable(
                DirectorJpaMapper
                        .mapper
                        .toDirectorDto(
                                directorJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public DirectorDto save(DirectorDto directorDto) {
        return DirectorJpaMapper
                .mapper
                .toDirectorDto(
                        directorJpaRepository
                                .save(
                                        DirectorJpaMapper
                                                .mapper
                                                .toDirectorEntity(directorDto)
                                )
                );
    }

    @Override
    public void delete(DirectorDto directorDto) {
        directorJpaRepository
                .delete(
                        DirectorJpaMapper
                                .mapper
                                .toDirectorEntity(directorDto)
                );
    }
}
