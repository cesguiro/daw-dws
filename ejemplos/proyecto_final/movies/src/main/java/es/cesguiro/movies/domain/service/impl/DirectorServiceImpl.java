package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.common.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.mapper.DirectorDomainMapper;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.domain.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    final DirectorRepository directorRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public DirectorDto find(int id) {
        return DirectorDomainMapper
                .mapper
                .toDirectorDto(this.findDirector(id));
    }

    private Director findDirector(int id) {
        return directorRepository
                                .find(id)
                                .orElseThrow(
                                        () -> new ResourceNotFoundException("Director not found with id: " + id)
                                );

    }
    @Override
    public DirectorDto create(DirectorDto directorDto) {
        return DirectorDomainMapper
                .mapper
                .toDirectorDto(
                        directorRepository
                                .save(
                                        DirectorDomainMapper
                                                .mapper
                                                .toDirector(directorDto)
                                )
                );
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) {
        Director director = this.findDirector(directorDto.getId());
        DirectorDomainMapper.mapper.updateDirectorFromDirectorDto(directorDto, director);
        return DirectorDomainMapper
                .mapper
                .toDirectorDto(
                        directorRepository
                                .save(director)
                );
    }

    @Override
    public void delete(int id) {
        Director director = this.findDirector(id);
        directorRepository.delete(director);
    }

}
