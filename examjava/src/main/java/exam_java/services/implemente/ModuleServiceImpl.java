package exam_java.services.implemente;

import exam_java.entities.Module;
import exam_java.Repositories.ModuleRepository;
import exam_java.services.ModuleService;

import java.util.List;
import java.util.Optional;

public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public void ajouterModule(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public void supprimerModule(Module module) {
        moduleRepository.delete(module);
    }

    @Override
    public List<Module> listerModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Module> rechercherModuleParId(int id) {
        return moduleRepository.findById(id);
    }
}
