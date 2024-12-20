package exam_java.Repositories.list;

import exam_java.Repositories.CoursRepository;
import exam_java.entities.Cours;

import java.util.Optional;

public class CoursRepositoryListImpl extends RepositoryListImpl<Cours> implements CoursRepository {

    @Override
    public Optional<Cours> findById(Object id) {
        return data.stream()
                .filter(cours -> cours.getId() == (int) id)
                .findFirst();
    }
}
