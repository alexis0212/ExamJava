package exam_java.Repositories.list;

import exam_java.Repositories.ClasseRepository;
import exam_java.entities.Classe;

import java.util.Optional;

public class ClasseRepositoryListImpl extends RepositoryListImpl<Classe> implements ClasseRepository {

    @Override
    public Optional<Classe> findById(Object id) {
        return data.stream()
                .filter(classe -> classe.getId() == (int) id)
                .findFirst();
    }
}
