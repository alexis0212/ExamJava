package projet_java_s5.services.Implemente;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Paiement;
import projet_java_s5.repositories.PaiementRepository;
import projet_java_s5.repositories.list.PaiementRepositoryListImpl;
import projet_java_s5.services.DetteService;
import projet_java_s5.services.PaiementService;

import java.util.List;

public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository paiementRepository;
    private final DetteService detteService;

    public PaiementServiceImpl(PaiementRepository paiementRepository, DetteService detteService) {
        this.paiementRepository = paiementRepository;
        this.detteService = detteService;
    }


    
    @Override
    public void ajouterPaiement(Paiement paiement) {
        paiementRepository.ajouterPaiement(paiement);
        System.out.println("Paiement enregistré et dette mise à jour.");
    }

    @Override
    public List<Paiement> listerTousLesPaiements() {
        return paiementRepository.listerTousLesPaiements();
    }

    @Override
    public Paiement rechercherPaiementParId(int id) {
        return paiementRepository.rechercherPaiementParId(id);
    }

    @Override
    public void supprimerPaiement(int id) {
        paiementRepository.supprimerPaiement(id);
    }

    @Override
    public List<Paiement> listerPaiementsParClient(Client client) {
        return paiementRepository.listerPaiementsParClient(client);
    }
}
