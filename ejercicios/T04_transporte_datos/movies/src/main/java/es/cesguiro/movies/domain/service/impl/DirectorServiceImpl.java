package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.DirectorRepository;
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
    public void update(Director director) {
        directorRepository.find(director.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + director.getId()));
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }

    @Override
    public Director find(int id) {
        Director director = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
        return director;
    }
}
