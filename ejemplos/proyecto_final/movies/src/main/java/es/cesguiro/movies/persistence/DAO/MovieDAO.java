package es.cesguiro.movies.persistence.DAO;

import es.cesguiro.movies.persistence.entity.impl.MovieEntity;

public interface MovieDAO extends GenericDAO<MovieEntity, Integer>{

    //Optional<List<MovieListDTO>> paginate(Integer page, Integer pageSize);

    //Optional<MovieDetailDTO> findById(int id);
}
