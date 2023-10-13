package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
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
    public Director update(int id, Director director) {
        /*Director existingDirector = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));

        existingDirector.setName(director.getName());
        existingDirector.setBirthYear(director.getBirthYear());
        existingDirector.setDeathYear(director.getDeathYear());

        return directorRepository.update(existingDirector);*/
        return null;
    }
}
