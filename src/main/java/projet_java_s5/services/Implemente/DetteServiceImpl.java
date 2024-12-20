package projet_java_s5.services.Implemente;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Dette;
import projet_java_s5.repositories.DetteRepository;
import projet_java_s5.services.DetteService;

import java.util.List;

public class DetteServiceImpl implements DetteService {
    private final DetteRepository detteRepository;

    // Le constructeur accepte un DetteRepository générique (soit en mémoire, soit en base de données)
    public DetteServiceImpl(DetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    @Override
    public void ajouterDette(Dette dette) {
        dette.setMontantRestant(dette.getMontant());
        detteRepository.ajouterDette(dette);
        System.out.println("Dette ajoutée avec succès : " + dette);
    }

    @Override
    public List<Dette> obtenirDettesNonSoldees() {
        return detteRepository.obtenirDettesNonSoldees();
    }

    @Override
    public List<Dette> filtrerDettesParEtat(String etat) {
        return detteRepository.filtrerDettesParEtat(etat);
    }

    @Override
    public List<Dette> obtenirDettesSoldees() {
        return detteRepository.obtenirDettesSoldees();
    }

    @Override
    public void archiverDettesSoldees(List<Dette> dettesSoldees) {
        detteRepository.archiverDettesSoldees(dettesSoldees);
        System.out.println("Dettes soldées archivées avec succès.");
    }

    @Override
    public void modifierDette(Client client, double nouveauMontant, String nouvelEtat) {
        detteRepository.modifierDette(client, nouveauMontant, nouvelEtat);
    }

    @Override
    public void supprimerDette(Client client) {
        detteRepository.supprimerDette(client);
    }
}
