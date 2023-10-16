package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.dto.director.DirectorDetailDTO;
import es.cesguiro.movies.dto.director.DirectorInsertDTO;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.dto.director.DirectorUpdateDTO;
import es.cesguiro.movies.dto.mapper.DirectorMapper;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int create(DirectorInsertDTO directorInsertDTO) {
        return directorRepository.insert(DirectorMapper.mapper.toModel(directorInsertDTO));
    }

    @Override
    public void update(DirectorUpdateDTO directorUpdateDTO) {
        Director director = directorRepository.find(directorUpdateDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + directorUpdateDTO.getId()));
        director.setName(directorUpdateDTO.getName());
        director.setBirthYear(directorUpdateDTO.getBirthYear());
        director.setDeathYear(directorUpdateDTO.getDeathYear());
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        Director director = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }

    @Override
    public DirectorDetailDTO find(int id) {
        Director director = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
        return DirectorMapper.mapper.toDetailDTO(director);
    }
}
