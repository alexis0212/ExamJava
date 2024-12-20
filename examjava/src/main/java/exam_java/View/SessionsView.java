package exam_java.View;

import exam_java.entities.Sessions;
import exam_java.services.SessionsService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SessionsView {
    private final SessionsService sessionsService;
    private final Scanner scanner = new Scanner(System.in);

    public SessionsView(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    public void afficherMenu() {
        System.out.println("=== GESTION DES SESSIONS ===");
        System.out.println("1. Ajouter une session");
        System.out.println("2. Supprimer une session");
        System.out.println("3. Lister toutes les sessions");
        System.out.println("4. Rechercher une session par ID");
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
                case 1 -> ajouterSession();
                case 2 -> supprimerSession();
                case 3 -> listerSessions();
                case 4 -> rechercherSession();
                case 5 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void ajouterSession() {
        System.out.print("Date de la session (AAAA-MM-JJ HH:mm:ss) : ");
        String date = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(date);

        System.out.print("Salle : ");
        String salle = scanner.nextLine();

        System.out.print("En ligne ? (true/false) : ");
        boolean enLigne = scanner.nextBoolean();

        Sessions session = new Sessions(0, dateTime.toLocalDate(), dateTime.toLocalTime(), dateTime.toLocalTime().plusHours(2), salle, enLigne, 0);
        sessionsService.ajouterSession(session);
        System.out.println("Session ajoutée avec succès !");
    }

    private void supprimerSession() {
        System.out.print("ID de la session à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        sessionsService.rechercherSessionParId(id).ifPresentOrElse(
                session -> {
                    sessionsService.supprimerSession(session);
                    System.out.println("Session supprimée avec succès !");
                },
                () -> System.out.println("Session introuvable.")
        );
    }

    private void listerSessions() {
        sessionsService.listerSessions().forEach(session ->
                System.out.println("ID : " + session.getId() +
                        ", Date : " + session.getDate() +
                        ", Heure Début : " + session.getHeureDebut() +
                        ", Salle : " + session.getSalle())
        );
    }

    private void rechercherSession() {
        System.out.print("ID de la session à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        sessionsService.rechercherSessionParId(id).ifPresentOrElse(
                session -> System.out.println("ID : " + session.getId() +
                        ", Date : " + session.getDate() +
                        ", Heure Début : " + session.getHeureDebut() +
                        ", Salle : " + session.getSalle()),
                () -> System.out.println("Session introuvable.")
        );
    }
}
