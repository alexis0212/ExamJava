package exam_java.Repositories.list;

import exam_java.Repositories.ProfesseurRepository;
import exam_java.entities.Professeurs;

import java.util.Optional;

public class ProfesseursRepositoryListImpl extends RepositoryListImpl<Professeurs> implements ProfesseurRepository {

    @Override
    public Optional<Professeurs> findById(Object id) {
        return data.stream()
                .filter(professeur -> professeur.getId() == (int) id)
                .findFirst();
    }
}
