package exam_java.Repositories.list;

import exam_java.Repositories.NiveauRepository;
import exam_java.entities.Niveau;

import java.util.Optional;

public class NiveauRepositoryListImpl extends RepositoryListImpl<Niveau> implements NiveauRepository {

    @Override
    public Optional<Niveau> findById(Object id) {
        return data.stream()
                .filter(niveau -> niveau.getId() == (int) id)
                .findFirst();
    }
}
