package exam_java.Repositories.list;


import exam_java.core.Repository.Repository;

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

    @Override
    public Optional<T> findById(Object id) {
        return data.stream().filter(e -> e.hashCode() == id.hashCode()).findFirst();
    }
}

