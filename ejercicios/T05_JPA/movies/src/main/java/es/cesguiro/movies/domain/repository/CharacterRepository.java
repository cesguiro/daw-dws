package es.cesguiro.movies.domain.repository;


import es.cesguiro.movies.domain.entity.CharacterMovie;
import org.springframework.stereotype.Component;

@Component
public interface CharacterRepository {

    CharacterMovie find(int id);

}
