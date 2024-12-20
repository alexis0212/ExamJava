package exam_java.Repositories;


import exam_java.entities.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository {
    
    void save(Module module);
    void delete(Module module);
    List<Module> findAll();
    Optional<Module> findById(Object id);
}

