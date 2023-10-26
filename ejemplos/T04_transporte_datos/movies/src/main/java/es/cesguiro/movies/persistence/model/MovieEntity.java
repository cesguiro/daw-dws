package es.cesguiro.movies.persistence.model;

import es.cesguiro.movies.persistence.dao.ActorDAO;
import es.cesguiro.movies.persistence.dao.CharacterDAO;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorEntity directorEntity;
    private List<CharacterEntity> characterEntities;


    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public DirectorEntity getDirectorEntity(Connection connection, DirectorDAO directorDAO) {
        if(this.directorEntity == null) {
            this.directorEntity = directorDAO.findByMovieId(connection, id).orElse(null);
        }
        return this.directorEntity;
    }

    public List<CharacterEntity> getCharacterEntities(Connection connection, CharacterDAO characterDAO) {
        if(this.characterEntities == null) {
            this.characterEntities = characterDAO.findByMovieId(connection, id);
        }
        return this.characterEntities;
    }


}
