package projet_java_s5.views;

import projet_java_s5.entities.Paiement;
import projet_java_s5.entities.Client;
import projet_java_s5.services.Implemente.PaiementServiceImpl;
import projet_java_s5.services.Implemente.ClientServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PaiementViews {
    private PaiementServiceImpl paiementService;
    private ClientServiceImpl clientService;
    private Scanner scanner;

    public PaiementViews(PaiementServiceImpl paiementService, ClientServiceImpl clientService) {
        this.paiementService = paiementService;
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    public void listerTousLesPaiements() {
        List<Paiement> paiements = paiementService.listerTousLesPaiements();
        if (paiements.isEmpty()) {
            System.out.println("Aucun paiement trouvé.");
        } else {
            System.out.println("Liste des paiements :");
            for (Paiement paiement : paiements) {
                System.out.println("ID: " + paiement.getId() + ", Montant: " + paiement.getMontant() + ", Client: " + paiement.getClient().getNom() + ", Date: " + paiement.getDate());
            }
        }
    }

    public void supprimerPaiement() {
        System.out.print("Entrez l'ID du paiement à supprimer : ");
        int idPaiement = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Paiement paiementASupprimer = paiementService.rechercherPaiementParId(idPaiement);
        if (paiementASupprimer != null) {
            paiementService.supprimerPaiement(idPaiement);
            System.out.println("Paiement supprimé avec succès.");
        } else {
            System.out.println("Erreur : Paiement non trouvé.");
        }
    }

    public void enregistrerPaiement() {
        System.out.print("Entrez le montant du paiement : ");
        double montantPaiement = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephoneClient = scanner.nextLine();
        Client client = clientService.rechercherClientParTelephone(telephoneClient);

        if (client != null) {
            Paiement paiement = new Paiement();
            paiement.setMontant(montantPaiement);
            paiement.setClient(client);
            paiement.setDate(new Date()); // Date du paiement
            paiementService.ajouterPaiement(paiement);
            System.out.println("Paiement enregistré avec succès.");
        } else {
            System.out.println("Client non trouvé, le paiement ne sera pas enregistré.");
        }
    }

    public void listerPaiementsParClient() {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephoneClient = scanner.nextLine();
        Client client = clientService.rechercherClientParTelephone(telephoneClient);
    
        if (client != null) {
            List<Paiement> paiements = paiementService.listerPaiementsParClient(client);
            if (paiements.isEmpty()) {
                System.out.println("Aucun paiement trouvé pour ce client.");
            } else {
                System.out.println("Paiements effectués par " + client.getNom() + " :");
                for (Paiement paiement : paiements) {
                    System.out.println("ID: " + paiement.getId() + ", Montant: " + paiement.getMontant() + ", Date: " + paiement.getDate());
                }
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }
}
