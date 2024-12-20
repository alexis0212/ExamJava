package exam_java.services;

import exam_java.entities.Professeurs;

import java.util.List;
import java.util.Optional;

public interface ProfesseursService {
    void ajouterProfesseur(Professeurs professeur);
    void supprimerProfesseur(Professeurs professeur);
    List<Professeurs> listerProfesseurs();
    Optional<Professeurs> rechercherProfesseurParId(int id);
}
