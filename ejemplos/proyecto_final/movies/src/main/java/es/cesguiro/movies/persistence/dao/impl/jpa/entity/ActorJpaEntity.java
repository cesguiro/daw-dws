package es.cesguiro.movies.persistence.dao.impl.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actors")
@Data
@NoArgsConstructor
public class ActorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birth_year", nullable = true)
    private Integer birthYear;
    @Column(name = "death_year", nullable = true)
    private Integer deathYear;
}
