package exam_java.services;

import exam_java.entities.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {
    void ajouterModule(Module module);
    void supprimerModule(Module module);
    List<Module> listerModules();
    Optional<Module> rechercherModuleParId(int id);
}
