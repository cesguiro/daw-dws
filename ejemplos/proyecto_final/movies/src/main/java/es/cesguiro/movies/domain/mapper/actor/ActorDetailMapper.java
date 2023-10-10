package es.cesguiro.movies.domain.mapper.actor;

import es.cesguiro.movies.controller.DTO.actor.ActorDetailDTO;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.entity.impl.ActorEntity;

public class ActorDetailMapper {

    public ActorDetailDTO toDTO(Actor actor) {
        return new ActorDetailDTO(
                actor.getId(),
                actor.getName(),
                actor.getBirthYear(),
                actor.getDeathYear()
        );
    }

    public Actor fromEntity(ActorEntity actorEntity) {
        return new Actor(
                actorEntity.getId(),
                actorEntity.getName(),
                actorEntity.getBirthYear(),
                actorEntity.getDeathYear()
        );
    }

}
