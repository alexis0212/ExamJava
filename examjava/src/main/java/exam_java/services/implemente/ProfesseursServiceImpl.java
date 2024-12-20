package exam_java.services.implemente;

import exam_java.entities.Professeurs;
import exam_java.Repositories.ProfesseurRepository;
import exam_java.services.ProfesseursService;

import java.util.List;
import java.util.Optional;

public class ProfesseursServiceImpl implements ProfesseursService {
    private final ProfesseurRepository professeurRepository;

    public ProfesseursServiceImpl(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public void ajouterProfesseur(Professeurs professeur) {
        professeurRepository.save(professeur);
    }

    @Override
    public void supprimerProfesseur(Professeurs professeur) {
        professeurRepository.delete(professeur);
    }

    @Override
    public List<Professeurs> listerProfesseurs() {
        return professeurRepository.findAll();
    }

    @Override
    public Optional<Professeurs> rechercherProfesseurParId(int id) {
        return professeurRepository.findById(id);
    }
}
