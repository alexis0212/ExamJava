package exam_java.View;

import exam_java.entities.Cours;
import exam_java.entities.Classe;
import exam_java.entities.Module;
import exam_java.entities.Professeurs;
import exam_java.services.CoursService;

import java.util.Scanner;

public class CoursView {
    private final CoursService coursService;
    private final Scanner scanner = new Scanner(System.in);

    public CoursView(CoursService coursService) {
        this.coursService = coursService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES COURS ===");
        System.out.println("1. Ajouter un cours");
        System.out.println("2. Supprimer un cours");
        System.out.println("3. Lister tous les cours");
        System.out.println("4. Rechercher un cours par ID");
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
                case 1 -> ajouterCours();
                case 2 -> supprimerCours();
                case 3 -> listerCours();
                case 4 -> rechercherCours();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterCours() {
        System.out.print("Nom du professeur : ");
        String nomProf = scanner.nextLine();
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        System.out.print("Nom du module : ");
        String nomModule = scanner.nextLine();

        Professeurs professeur = new Professeurs(0, nomProf, ""); // Remplir les détails nécessaires
        Classe classe = new Classe(0, nomClasse, null);
        Module module = new Module(0, nomModule);

        Cours cours = new Cours(0, professeur, classe, module);
        coursService.ajouterCours(cours);
        System.out.println("Cours ajouté avec succès !");
    }

    private void supprimerCours() {
        System.out.print("ID du cours à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        coursService.rechercherCoursParId(id).ifPresentOrElse(
                cours -> {
                    coursService.supprimerCours(cours);
                    System.out.println("Cours supprimé avec succès !");
                },
                () -> System.out.println("Cours introuvable.")
        );
    }

    private void listerCours() {
        coursService.listerCours().forEach(cours ->
                System.out.println("ID : " + cours.getId() +
                        ", Professeur : " + cours.getProfesseur().getNom() +
                        ", Classe : " + cours.getClasses().getNom() +
                        ", Module : " + cours.getModule().getNom())
        );
    }

    private void rechercherCours() {
        System.out.print("ID du cours à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        coursService.rechercherCoursParId(id).ifPresentOrElse(
                cours -> System.out.println("ID : " + cours.getId() +
                        ", Professeur : " + cours.getProfesseur().getNom() +
                        ", Classe : " + cours.getClasses().getNom() +
                        ", Module : " + cours.getModule().getNom()),
                () -> System.out.println("Cours introuvable.")
        );
    }
}
