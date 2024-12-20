package projet_java_s5.repositories.list;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Dette;
import projet_java_s5.repositories.DetteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetteRepositoryListImpl extends RepositoryListImpl<Dette> implements DetteRepository {

    @Override
    public Optional<Dette> findById(Object client) {
        return data.stream()
                .filter(dette -> dette.getClient().equals(client))
                .findFirst();
    }

    @Override
    public void ajouterDette(Dette dette) {
        save(dette);
    }

    @Override
    public List<Dette> obtenirDettesNonSoldees() {
        return data.stream()
                .filter(dette -> !dette.isSoldee())
                .collect(Collectors.toList());
    }

    @Override
    public List<Dette> filtrerDettesParEtat(String etat) {
        return data.stream()
                .filter(dette -> dette.getEtat().equalsIgnoreCase(etat))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dette> obtenirDettesSoldees() {
        return data.stream()
                .filter(Dette::isSoldee)
                .collect(Collectors.toList());
    }

    @Override
    public void archiverDettesSoldees(List<Dette> dettesSoldees) {
        data.removeAll(dettesSoldees);
    }

    // Implémentation de la méthode modifierDette
    @Override
    public void modifierDette(Client client, double nouveauMontant, String nouvelEtat) {
        for (Dette dette : data) {
            if (dette.getClient().equals(client)) {
                dette.setMontant(nouveauMontant);  // Mise à jour du montant de la dette
                dette.setEtat(nouvelEtat);  // Mise à jour de l'état de la dette
                System.out.println("Dette modifiée avec succès pour le client : " + client.getNom());
                return;
            }
        }
        System.out.println("Aucune dette trouvée pour le client : " + client.getNom());
    }

    // Implémentation de la méthode supprimerDette
    @Override
    public void supprimerDette(Client client) {
        data.removeIf(dette -> dette.getClient().equals(client)); // Suppression de la dette pour le client
        System.out.println("Dette supprimée pour le client : " + client.getNom());
    }
}
