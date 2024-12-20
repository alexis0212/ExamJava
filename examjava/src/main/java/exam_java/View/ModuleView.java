package exam_java.View;

import exam_java.entities.Module;
import exam_java.services.ModuleService;

import java.util.Scanner;

public class ModuleView {
    private final ModuleService moduleService;
    private final Scanner scanner = new Scanner(System.in);

    public ModuleView(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES MODULES ===");
        System.out.println("1. Ajouter un module");
        System.out.println("2. Supprimer un module");
        System.out.println("3. Lister tous les modules");
        System.out.println("4. Rechercher un module par ID");
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
                case 1 -> ajouterModule();
                case 2 -> supprimerModule();
                case 3 -> listerModules();
                case 4 -> rechercherModule();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterModule() {
        System.out.print("Nom du module : ");
        String nom = scanner.nextLine();
        Module module = new Module(0, nom);
        moduleService.ajouterModule(module);
        System.out.println("Module ajouté avec succès !");
    }

    private void supprimerModule() {
        System.out.print("ID du module à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        moduleService.rechercherModuleParId(id).ifPresentOrElse(
                module -> {
                    moduleService.supprimerModule(module);
                    System.out.println("Module supprimé avec succès !");
                },
                () -> System.out.println("Module introuvable.")
        );
    }

    private void listerModules() {
        moduleService.listerModules().forEach(module ->
                System.out.println("ID : " + module.getId() + ", Nom : " + module.getNom())
        );
    }

    private void rechercherModule() {
        System.out.print("ID du module à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        moduleService.rechercherModuleParId(id).ifPresentOrElse(
                module -> System.out.println("ID : " + module.getId() + ", Nom : " + module.getNom()),
                () -> System.out.println("Module introuvable.")
        );
    }
}
