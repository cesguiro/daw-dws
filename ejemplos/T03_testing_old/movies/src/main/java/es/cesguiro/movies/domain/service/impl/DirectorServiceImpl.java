package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.persistence.DirectorRepository;
import es.cesguiro.movies.persistence.impl.DirectorRepositoryImpl;

public class DirectorServiceImpl implements DirectorService {

    DirectorRepository directorRepository = new DirectorRepositoryImpl();

    @Override
    public void create(Director director) {
        directorRepository.insert(director);
    }
}
