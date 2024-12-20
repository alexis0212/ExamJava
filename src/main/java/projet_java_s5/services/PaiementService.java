package projet_java_s5.services;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Paiement;

import java.util.List;

public interface PaiementService {
    void ajouterPaiement(Paiement paiement);
    List<Paiement> listerTousLesPaiements();
    Paiement rechercherPaiementParId(int id);
    void supprimerPaiement(int id);
    List<Paiement> listerPaiementsParClient(Client client); // Nouvelle méthode

    }
