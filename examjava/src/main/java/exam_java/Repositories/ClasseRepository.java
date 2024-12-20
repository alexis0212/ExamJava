package exam_java.Repositories;


import exam_java.entities.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository {
    void save(Classe classe);
    void delete(Classe classe);
    List<Classe> findAll();
    Optional<Classe> findById(Object id);
}

