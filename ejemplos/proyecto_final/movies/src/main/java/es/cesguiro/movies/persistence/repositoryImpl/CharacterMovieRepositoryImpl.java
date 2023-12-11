package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.repository.CharacterMovieRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterMovieRepositoryImpl implements CharacterMovieRepository {
    @Override
    public CharacterMovie find(int id) {
        return null;
    }
}
