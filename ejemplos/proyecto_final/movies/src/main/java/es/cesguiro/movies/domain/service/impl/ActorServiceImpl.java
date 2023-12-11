package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.common.exception.ResourceNotFoundException;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.mapper.ActorDomainMapper;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.domain.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorDto find(int id) {
        return ActorDomainMapper
                .mapper
                .toActorDto(this.findActor(id));
    }

    private Actor findActor(int id) {
        return actorRepository
                .find(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Actor not found with id: " + id)
                );

    }
    @Override
    public ActorDto create(ActorDto actorDto) {
        return ActorDomainMapper
                .mapper
                .toActorDto(
                        actorRepository
                                .save(
                                        ActorDomainMapper
                                                .mapper
                                                .toActor(actorDto)
                                )
                );
    }

    @Override
    public ActorDto update(ActorDto actorDto) {
        Actor actor = this.findActor(actorDto.getId());
        ActorDomainMapper.mapper.updateActorFromActorDto(actorDto, actor);
        return ActorDomainMapper
                .mapper
                .toActorDto(
                        actorRepository
                                .save(actor)
                );
    }

    @Override
    public void delete(int id) {
        Actor actor = this.findActor(id);
        actorRepository.delete(actor);
    }
}
