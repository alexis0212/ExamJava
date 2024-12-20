package exam_java.View;

import exam_java.entities.Professeurs;
import exam_java.services.ProfesseursService;

import java.util.Scanner;

public class ProfesseursView {
    private final ProfesseursService professeursService;
    private final Scanner scanner = new Scanner(System.in);

    public ProfesseursView(ProfesseursService professeursService) {
        this.professeursService = professeursService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES PROFESSEURS ===");
        System.out.println("1. Ajouter un professeur");
        System.out.println("2. Supprimer un professeur");
        System.out.println("3. Lister tous les professeurs");
        System.out.println("4. Rechercher un professeur par ID");
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
                case 1 -> ajouterProfesseur();
                case 2 -> supprimerProfesseur();
                case 3 -> listerProfesseurs();
                case 4 -> rechercherProfesseur();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterProfesseur() {
        System.out.print("Nom du professeur : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom du professeur : ");
        String prenom = scanner.nextLine();

        Professeurs professeur = new Professeurs(0, nom, prenom);
        professeursService.ajouterProfesseur(professeur);
        System.out.println("Professeur ajouté avec succès !");
    }

    private void supprimerProfesseur() {
        System.out.print("ID du professeur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        professeursService.rechercherProfesseurParId(id).ifPresentOrElse(
                professeur -> {
                    professeursService.supprimerProfesseur(professeur);
                    System.out.println("Professeur supprimé avec succès !");
                },
                () -> System.out.println("Professeur introuvable.")
        );
    }

    private void listerProfesseurs() {
        professeursService.listerProfesseurs().forEach(professeur ->
                System.out.println("ID : " + professeur.getId() +
                        ", Nom : " + professeur.getNom() +
                        ", Prénom : " + professeur.getPrenom())
        );
    }

    private void rechercherProfesseur() {
        System.out.print("ID du professeur à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        professeursService.rechercherProfesseurParId(id).ifPresentOrElse(
                professeur -> System.out.println("ID : " + professeur.getId() +
                        ", Nom : " + professeur.getNom() +
                        ", Prénom : " + professeur.getPrenom()),
                () -> System.out.println("Professeur introuvable.")
        );
    }
}
