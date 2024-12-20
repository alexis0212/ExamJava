package exam_java.Repositories;


import exam_java.entities.Niveau;

import java.util.List;
import java.util.Optional;

public interface NiveauRepository {
    
    void save(Niveau niveau);
    void delete(Niveau niveau);
    List<Niveau> findAll();
    Optional<Niveau> findById(Object id);
}
