package exam_java.services.implemente;

import exam_java.entities.Cours;
import exam_java.Repositories.CoursRepository;
import exam_java.services.CoursService;

import java.util.List;
import java.util.Optional;

public class CoursServiceImpl implements CoursService {
    private final CoursRepository coursRepository;

    public CoursServiceImpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public void ajouterCours(Cours cours) {
        coursRepository.save(cours);
    }

    @Override
    public void supprimerCours(Cours cours) {
        coursRepository.delete(cours);
    }

    @Override
    public List<Cours> listerCours() {
        return coursRepository.findAll();
    }

    @Override
    public Optional<Cours> rechercherCoursParId(int id) {
        return coursRepository.findById(id);
    }
}
