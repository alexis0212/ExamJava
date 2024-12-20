package exam_java.Repositories.list;

import exam_java.Repositories.ModuleRepository;
import exam_java.entities.Module;

import java.util.Optional;

public class ModuleRepositoryListImpl extends RepositoryListImpl<Module> implements ModuleRepository {

    @Override
    public Optional<Module> findById(Object id) {
        return data.stream()
                .filter(module -> module.getId() == (int) id)
                .findFirst();
    }
}
