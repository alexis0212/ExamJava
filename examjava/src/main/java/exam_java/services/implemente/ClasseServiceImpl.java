package exam_java.services.implemente;

import exam_java.entities.Classe;
import exam_java.Repositories.ClasseRepository;
import exam_java.services.ClasseService;

import java.util.List;
import java.util.Optional;

public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;

    public ClasseServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public void ajouterClasse(Classe classe) {
        classeRepository.save(classe);
    }

    @Override
    public void supprimerClasse(Classe classe) {
        classeRepository.delete(classe);
    }

    @Override
    public List<Classe> listerClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Optional<Classe> rechercherClasseParId(int id) {
        return classeRepository.findById(id);
    }
}
