package es.cesguiro.movies.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
public class DirectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "death_year", nullable = true)
    private Integer deathYear;
}
