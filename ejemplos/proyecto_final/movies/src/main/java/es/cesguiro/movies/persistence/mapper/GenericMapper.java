package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.persistence.entity.GenericEntity;
import org.springframework.jdbc.core.RowMapper;

public interface GenericMapper<T extends GenericEntity> extends RowMapper<T> {
}
