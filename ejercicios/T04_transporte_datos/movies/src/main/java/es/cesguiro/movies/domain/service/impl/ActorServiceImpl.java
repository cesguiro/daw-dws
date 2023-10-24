package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.dto.ActorDTO;
import es.cesguiro.movies.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;
    @Override
    public int create(ActorDTO actorDTO) {
        Actor actor = ActorMapper.mapper.toActor(actorDTO);
        return actorRepository.insert(ActorMapper.mapper.toActorDTO(actor));
    }

    @Override
    public void update(ActorDTO actorDTO) {
        actorRepository.find(actorDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actorDTO.getId()));
        Actor actor = ActorMapper.mapper.toActor(actorDTO);
        actorRepository.update(ActorMapper.mapper.toActorDTO(actor));
    }

    @Override
    public void delete(int id) {
        actorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        actorRepository.delete(id);
    }

    @Override
    public ActorDTO find(int id) {
        ActorDTO actorDTO = actorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        return actorDTO;
    }
}
