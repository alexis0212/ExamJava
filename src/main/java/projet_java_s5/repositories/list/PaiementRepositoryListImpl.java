package projet_java_s5.repositories.list;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Paiement;
import projet_java_s5.repositories.PaiementRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaiementRepositoryListImpl extends RepositoryListImpl<Paiement> implements PaiementRepository {
    private static int currentId = 1;

    @Override
    public Optional<Paiement> findById(Object id) {
        return data.stream()
                .filter(paiement -> paiement.getId() == (int) id)
                .findFirst();
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        paiement.setId(currentId++);
        save(paiement);
    }

    @Override
    public List<Paiement> listerTousLesPaiements() {
        return findAll(); // Return all payments from the inherited data list
    }

    @Override
    public List<Paiement> listerPaiementsParClient(Client client) {
        return data.stream()
                .filter(p -> p.getClient().equals(client))
                .collect(Collectors.toList());
    }

    @Override
    public Paiement rechercherPaiementParId(int id) {
        return findById(id).orElse(null); // Return the payment if found, or null if not found
    }

    @Override
    public void supprimerPaiement(int id) {
        findById(id).ifPresent(this::delete); // Delete the payment if found by ID
    }
}
