package exam_java.services;

import exam_java.entities.Cours;

import java.util.List;
import java.util.Optional;

public interface CoursService {
    void ajouterCours(Cours cours);
    void supprimerCours(Cours cours);
    List<Cours> listerCours();
    Optional<Cours> rechercherCoursParId(int id);
}
