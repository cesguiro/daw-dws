package es.cesguiro.movies.persistence.model;

import es.cesguiro.movies.persistence.dao.CharacterMovieDAO;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private int year;
    private int runtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private DirectorEntity directorEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<CharacterMovieEntity> characterMovieEntities;

}
