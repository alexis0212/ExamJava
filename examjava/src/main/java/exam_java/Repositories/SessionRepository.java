package exam_java.Repositories;

import exam_java.entities.Sessions;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    void save(Sessions session);
    void delete(Sessions session);
    List<Sessions> findAll();
    Optional<Sessions> findById(Object id);
}
