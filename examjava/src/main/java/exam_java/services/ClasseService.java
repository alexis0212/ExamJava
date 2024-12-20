package exam_java.services;

import exam_java.entities.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
    void ajouterClasse(Classe classe);
    void supprimerClasse(Classe classe);
    List<Classe> listerClasses();
    Optional<Classe> rechercherClasseParId(int id);
}
