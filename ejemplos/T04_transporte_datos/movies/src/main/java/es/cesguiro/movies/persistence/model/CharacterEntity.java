package es.cesguiro.movies.persistence.model;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.dao.ActorDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
@NoArgsConstructor
public class CharacterEntity {

    int id;
    String characters;

    //Lazy loading
    ActorEntity actorEntity;

    public CharacterEntity(int id, String characters) {
        this.id = id;
        this.characters = characters;
    }

    public ActorEntity getActor(Connection connection, ActorDAO actorDAO) {
        if(this.actorEntity == null) {
            this.actorEntity = actorDAO.findByCharacterId(connection, this.id).orElse(null);
        }
        return actorEntity;
    }
}
