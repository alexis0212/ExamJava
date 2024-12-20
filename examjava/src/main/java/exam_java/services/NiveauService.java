package exam_java.services;

import exam_java.entities.Niveau;

import java.util.List;
import java.util.Optional;

public interface NiveauService {
    void ajouterNiveau(Niveau niveau);
    void supprimerNiveau(Niveau niveau);
    List<Niveau> listerNiveaux();
    Optional<Niveau> rechercherNiveauParId(int id);
}
