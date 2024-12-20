package exam_java.View;

import exam_java.entities.Classe;
import exam_java.entities.Niveau;
import exam_java.services.ClasseService;
import exam_java.services.NiveauService;

import java.util.Scanner;

public class ClasseView {
    private final ClasseService classeService;
    private final NiveauService niveauService;
    private final Scanner scanner = new Scanner(System.in);

    public ClasseView(ClasseService classeService, NiveauService niveauService) {
        this.classeService = classeService;
        this.niveauService = niveauService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES CLASSES ===");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Supprimer une classe");
        System.out.println("3. Lister toutes les classes");
        System.out.println("4. Rechercher une classe par ID");
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
                case 1 -> ajouterClasse();
                case 2 -> supprimerClasse();
                case 3 -> listerClasses();
                case 4 -> rechercherClasse();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterClasse() {
        System.out.print("Nom de la classe : ");
        String nom = scanner.nextLine();

        System.out.print("ID du niveau : ");
        int niveauId = scanner.nextInt();
        scanner.nextLine();

        Niveau niveau = niveauService.rechercherNiveauParId(niveauId).orElse(null);
        if (niveau == null) {
            System.out.println("Niveau introuvable !");
            return;
        }

        Classe classe = new Classe(0, nom, niveau);
        classeService.ajouterClasse(classe);
        System.out.println("Classe ajoutée avec succès !");
    }

    private void supprimerClasse() {
        System.out.print("ID de la classe à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        classeService.rechercherClasseParId(id).ifPresentOrElse(
                classe -> {
                    classeService.supprimerClasse(classe);
                    System.out.println("Classe supprimée avec succès !");
                },
                () -> System.out.println("Classe introuvable.")
        );
    }

    private void listerClasses() {
        classeService.listerClasses().forEach(classe ->
                System.out.println("ID : " + classe.getId() +
                        ", Nom : " + classe.getNom() +
                        ", Niveau : " + classe.getNiveau().getNom())
        );
    }

    private void rechercherClasse() {
        System.out.print("ID de la classe à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        classeService.rechercherClasseParId(id).ifPresentOrElse(
                classe -> System.out.println("ID : " + classe.getId() +
                        ", Nom : " + classe.getNom() +
                        ", Niveau : " + classe.getNiveau().getNom()),
                () -> System.out.println("Classe introuvable.")
        );
    }
}
