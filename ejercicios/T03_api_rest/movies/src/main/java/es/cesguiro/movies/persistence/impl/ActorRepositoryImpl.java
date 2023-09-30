package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.ActorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
    @Override
    public int insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        Connection connection = DBUtil.open();
<<<<<<< HEAD
        DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
=======
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
>>>>>>> refs/remotes/origin/master
    }

}
