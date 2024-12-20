package exam_java.Repositories;

import exam_java.entities.Professeurs;

import java.util.List;
import java.util.Optional;

public interface ProfesseurRepository {
    void save(Professeurs professeur);
    void delete(Professeurs professeur);
    List<Professeurs> findAll();
    Optional<Professeurs> findById(Object id);
}
