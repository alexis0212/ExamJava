package projet_java_s5.services;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Dette;
import java.util.List;

public interface DetteService {
    void ajouterDette(Dette dette);
        List<Dette> obtenirDettesNonSoldees();
    List<Dette> filtrerDettesParEtat(String etat);
    List<Dette> obtenirDettesSoldees();
    void archiverDettesSoldees(List<Dette> dettesSoldees);
    void modifierDette(Client client, double nouveauMontant, String nouvelEtat);
    void supprimerDette(Client client);
}
