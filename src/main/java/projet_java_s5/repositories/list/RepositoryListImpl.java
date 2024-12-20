package projet_java_s5.repositories.list;

import projet_java_s5.core.Repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryListImpl<T> implements Repository<T> {
    protected List<T> data = new ArrayList<>();

    @Override
    public void save(T entity) {
        data.add(entity);
    }

    @Override
    public void delete(T entity) {
        data.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data);
    }
}
