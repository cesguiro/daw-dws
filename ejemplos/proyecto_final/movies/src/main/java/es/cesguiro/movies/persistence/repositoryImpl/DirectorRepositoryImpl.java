package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.persistence.dao.DirectorDao;
import es.cesguiro.movies.persistence.mapper.DirectorPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Qualifier("DirectorDaoJpaImpl")
    final DirectorDao directorDao;

    @Autowired
    public DirectorRepositoryImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Override
    public Optional<Director> find(int id) {
        return Optional.ofNullable(
                DirectorPersistenceMapper
                        .mapper
                        .toDirector(
                                directorDao
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Director save(Director director) {
        return DirectorPersistenceMapper
                .mapper
                .toDirector(
                        directorDao
                                .save(
                                        DirectorPersistenceMapper
                                                .mapper
                                                .toDirectorDto(director)
                                )
                );
    }

    @Override
    public void delete(Director director) {
        directorDao.delete(
                DirectorPersistenceMapper
                        .mapper
                        .toDirectorDto(director)
        );
    }
}
