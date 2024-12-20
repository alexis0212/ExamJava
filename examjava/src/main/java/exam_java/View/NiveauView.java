package exam_java.View;

import exam_java.entities.Niveau;
import exam_java.services.NiveauService;

import java.util.Scanner;

public class NiveauView {
    private final NiveauService niveauService;
    private final Scanner scanner = new Scanner(System.in);

    public NiveauView(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES NIVEAUX ===");
        System.out.println("1. Ajouter un niveau");
        System.out.println("2. Supprimer un niveau");
        System.out.println("3. Lister tous les niveaux");
        System.out.println("4. Rechercher un niveau par ID");
        System.out.println("5. Quitter");
    }

    public void gerer() {
        int choix;
        do {
            afficherMenu();
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterNiveau();
                case 2 -> supprimerNiveau();
                case 3 -> listerNiveaux();
                case 4 -> rechercherNiveau();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterNiveau() {
        System.out.print("Nom du niveau : ");
        String nom = scanner.nextLine();
        Niveau niveau = new Niveau(0, nom);
        niveauService.ajouterNiveau(niveau);
        System.out.println("Niveau ajouté avec succès !");
    }

    private void supprimerNiveau() {
        System.out.print("ID du niveau à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        niveauService.rechercherNiveauParId(id).ifPresentOrElse(
                niveau -> {
                    niveauService.supprimerNiveau(niveau);
                    System.out.println("Niveau supprimé avec succès !");
                },
                () -> System.out.println("Niveau introuvable.")
        );
    }

    private void listerNiveaux() {
        niveauService.listerNiveaux().forEach(niveau ->
                System.out.println("ID : " + niveau.getId() + ", Nom : " + niveau.getNom())
        );
    }

    private void rechercherNiveau() {
        System.out.print("ID du niveau à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        niveauService.rechercherNiveauParId(id).ifPresentOrElse(
                niveau -> System.out.println("ID : " + niveau.getId() + ", Nom : " + niveau.getNom()),
                () -> System.out.println("Niveau introuvable.")
        );
    }
}
