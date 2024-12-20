package projet_java_s5.core.Repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T entity);
    void delete(T entity);
    Optional<T> findById(Object id);
    List<T> findAll();
}
