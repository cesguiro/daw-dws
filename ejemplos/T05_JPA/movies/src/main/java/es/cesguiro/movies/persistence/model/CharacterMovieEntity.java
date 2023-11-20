package es.cesguiro.movies.persistence.model;

import es.cesguiro.movies.persistence.dao.ActorDAO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;

@Entity
@Table(name = "actors_movies")
@Data
@NoArgsConstructor
public class CharacterMovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private ActorEntity actorEntity;

}
