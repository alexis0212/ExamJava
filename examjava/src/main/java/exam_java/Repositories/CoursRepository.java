package exam_java.Repositories;


import exam_java.entities.Cours;

import java.util.List;
import java.util.Optional;

public interface CoursRepository {
    void save(Cours cours);
    void delete(Cours cours);
    List<Cours> findAll();
    Optional<Cours> findById(Object id);
}
