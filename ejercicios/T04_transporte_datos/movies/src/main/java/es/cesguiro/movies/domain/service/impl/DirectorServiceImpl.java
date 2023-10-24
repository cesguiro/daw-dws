package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.dto.DirectorDTO;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.mapper.DirectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int create(DirectorDTO directorDTO) {
        Director director = DirectorMapper.mapper.toDirector(directorDTO);
        return directorRepository.insert(DirectorMapper.mapper.toDirectorDTO(director));
    }

    @Override
    public void update(DirectorDTO directorDTO) {
        directorRepository.find(directorDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + directorDTO.getId()));
        Director director = DirectorMapper.mapper.toDirector(directorDTO);
        directorRepository.update(DirectorMapper.mapper.toDirectorDTO(director));
    }

    @Override
    public void delete(int id) {
        directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }

    @Override
    public DirectorDTO find(int id) {
        DirectorDTO directorDTO = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        return directorDTO;
    }
}
