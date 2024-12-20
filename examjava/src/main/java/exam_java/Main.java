package exam_java;

import exam_java.Repositories.Bd.*;
import exam_java.Repositories.list.*;
import exam_java.core.bd.DataBaseImpl;
import exam_java.services.implemente.*;
import exam_java.View.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Choisir entre Base de données et Listes en mémoire
        System.out.println("=== GESTION DES DONNÉES ===");
        System.out.println("1. Utiliser la Base de données");
        System.out.println("2. Utiliser les Listes en mémoire");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        // Configuration des repositories, services et vues
        if (choix == 1) {
            utiliserBaseDeDonnees();
        } else if (choix == 2) {
            utiliserListesEnMemoire();
        } else {
            System.out.println("Choix invalide. Le programme va se fermer.");
        }
    }

    private static void utiliserBaseDeDonnees() {
        // Initialisation des repositories
        var dataBase = new DataBaseImpl();
        var coursRepo = new CoursRepositoryBD(dataBase);
        var niveauRepo = new NiveauRepositoryBD(dataBase);
        var classeRepo = new ClasseRepositoryBD(dataBase);
        var profRepo = new ProfesseursRepositoryBD(dataBase);
        var moduleRepo = new ModuleRepositoryBD(dataBase);
        var sessionRepo = new SessionsRepositoryBD(dataBase);

        // Initialisation des services
        var coursService = new CoursServiceImpl(coursRepo);
        var niveauService = new NiveauServiceImpl(niveauRepo);
        var classeService = new ClasseServiceImpl(classeRepo);
        var profService = new ProfesseursServiceImpl(profRepo);
        var moduleService = new ModuleServiceImpl(moduleRepo);
        var sessionService = new SessionsServiceImpl(sessionRepo);

        // Initialisation des vues
        var coursView = new CoursView(coursService);
        var niveauView = new NiveauView(niveauService);
        var classeView = new ClasseView(classeService, niveauService);
        var profView = new ProfesseursView(profService);
        var moduleView = new ModuleView(moduleService);
        var sessionView = new SessionsView(sessionService);

        // Lancement du menu principal
        afficherMenuPrincipal(coursView, niveauView, classeView, profView, moduleView, sessionView);
    }

    private static void utiliserListesEnMemoire() {
        // Initialisation des repositories
        var coursRepo = new CoursRepositoryListImpl();
        var niveauRepo = new NiveauRepositoryListImpl();
        var classeRepo = new ClasseRepositoryListImpl();
        var profRepo = new ProfesseursRepositoryListImpl();
        var moduleRepo = new ModuleRepositoryListImpl();
        var sessionRepo = new SessionsRepositoryListImpl();

        // Initialisation des services
        var coursService = new CoursServiceImpl(coursRepo);
        var niveauService = new NiveauServiceImpl(niveauRepo);
        var classeService = new ClasseServiceImpl(classeRepo);
        var profService = new ProfesseursServiceImpl(profRepo);
        var moduleService = new ModuleServiceImpl(moduleRepo);
        var sessionService = new SessionsServiceImpl(sessionRepo);

        // Initialisation des vues
        var coursView = new CoursView(coursService);
        var niveauView = new NiveauView(niveauService);
        var classeView = new ClasseView(classeService, niveauService);
        var profView = new ProfesseursView(profService);
        var moduleView = new ModuleView(moduleService);
        var sessionView = new SessionsView(sessionService);

        // Lancement du menu principal
        afficherMenuPrincipal(coursView, niveauView, classeView, profView, moduleView, sessionView);
    }

    private static void afficherMenuPrincipal(CoursView coursView, NiveauView niveauView, ClasseView classeView,
                                              ProfesseursView profView, ModuleView moduleView, SessionsView sessionView) {
        int choix;
        do {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Gestion des Cours");
            System.out.println("2. Gestion des Niveaux");
            System.out.println("3. Gestion des Classes");
            System.out.println("4. Gestion des Professeurs");
            System.out.println("5. Gestion des Modules");
            System.out.println("6. Gestion des Sessions");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> coursView.gerer();
                case 2 -> niveauView.gerer();
                case 3 -> classeView.gerer();
                case 4 -> profView.gerer();
                case 5 -> moduleView.gerer();
                case 6 -> sessionView.gerer();
                case 7 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 7);
    }
}
