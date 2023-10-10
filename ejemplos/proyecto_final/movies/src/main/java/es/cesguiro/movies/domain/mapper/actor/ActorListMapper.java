package es.cesguiro.movies.domain.mapper.actor;

import es.cesguiro.movies.controller.DTO.actor.ActorListDTO;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.entity.impl.ActorEntity;

import java.util.ArrayList;
import java.util.List;

public class ActorListMapper {


    private ActorListDTO toDTO(Actor actor) {
        return new ActorListDTO(
                actor.getId(),
                actor.getName(),
                actor.getBirthYear(),
                actor.getDeathYear()
        );
    }

    private Actor fromEntity(ActorEntity actorEntity) {
        return new Actor(
                actorEntity.getId(),
                actorEntity.getName(),
                actorEntity.getBirthYear(),
                actorEntity.getDeathYear()
        );
    }
    public List<ActorListDTO> ToListDTO(List<Actor> actors) {
        List<ActorListDTO> actorListDTO = new ArrayList<>();
        for (Actor actor: actors) {
            actorListDTO.add(this.toDTO(actor));
        }
        return actorListDTO;
    }

    public List<Actor> fromListEntity(List<ActorEntity> actorEntities) {
        List<Actor> actors = new ArrayList<>();
        for (ActorEntity actorEntity : actorEntities) {
            actors.add(this.fromEntity(actorEntity));
        }
        return actors;
    }
}
