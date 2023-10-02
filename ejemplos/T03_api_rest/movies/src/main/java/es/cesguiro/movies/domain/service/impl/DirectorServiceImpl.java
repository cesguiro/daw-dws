package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int create(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public void update(int id, Director director) {
        Director existingDirector = directorRepository.find(id);
        if (existingDirector == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        director.setId(existingDirector.getId());
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        Director director = directorRepository.find(id);
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        directorRepository.delete(id);
    }
}
