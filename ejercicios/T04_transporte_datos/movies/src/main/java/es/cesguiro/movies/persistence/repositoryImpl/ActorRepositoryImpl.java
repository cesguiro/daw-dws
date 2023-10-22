package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.mapper.ActorMapper;
import es.cesguiro.movies.persistence.dao.ActorDAO;
import es.cesguiro.movies.persistence.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;
    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Actor> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDAO.find(connection, id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Actor> findByMovieId(int movieId) {
        try (Connection connection= DBUtil.open(true)){
            List<ActorEntity> actorEntities = actorDAO.findByMovieId(connection, movieId);
            List<Actor> actors = actorEntities.stream()
                    .map(actorEntity -> ActorMapper.mapper.toActor(actorEntity))
                    .toList();
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Actor actor) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
