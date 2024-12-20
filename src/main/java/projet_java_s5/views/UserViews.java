package projet_java_s5.views;

import projet_java_s5.entities.User;
import projet_java_s5.services.Implemente.UserServiceImpl;
import projet_java_s5.entities.Role;

import java.util.Scanner;

public class UserViews {
    private final UserServiceImpl userService;
    private final Scanner scanner;

    public UserViews(UserServiceImpl userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void ajouterUser() {
        // Demander à l'utilisateur de spécifier son login
        System.out.print("Entrez le login : ");
        String login = scanner.nextLine();
        
        System.out.print("Entrez le nom d'utilisateur : ");
        String nomUtilisateur = scanner.nextLine();
        System.out.print("Entrez l'email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez le mot de passe : ");
        String motDePasse = scanner.nextLine();

        // Demander à l'utilisateur de spécifier son rôle
        System.out.println("Choisissez un rôle : ");
        System.out.println("1. Admin");
        System.out.println("2. Boutiquier");
        System.out.println("3. Client");
        int choixRole = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne

        Role role = null;

        // Assigner le rôle en fonction du choix
        switch (choixRole) {
            case 1:
                role = new Role("Admin");
                break;
            case 2:
                role = new Role("Boutiquier");
                break;
            case 3:
                role = new Role("Client");
                break;
            default:
                System.out.println("Choix invalide, rôle par défaut 'Client' assigné.");
                role = new Role("Client");
                break;
        }

        // Création de l'utilisateur avec le rôle spécifié
        User user = new User();
        user.setLogin(login);  // Ajout du login
        user.setNomUtilisateur(nomUtilisateur);
        user.setEmail(email);
        user.setPassword(motDePasse);
        user.setRole(role);  // Assigner le rôle choisi

        // Appel du service pour ajouter l'utilisateur
        userService.creerUser(user);
        System.out.println("Utilisateur ajouté avec succès.");
    }

    public void listerUsers() {
        System.out.println("Liste des utilisateurs : ");
        userService.obtenirUsers().forEach(u -> {
            System.out.println("Nom d'utilisateur : " + u.getNomUtilisateur() + ", Email : " + u.getEmail());
        });
    }

    public void rechercherUser() {
        System.out.print("Entrez le nom d'utilisateur à rechercher : ");
        String nomUtilisateur = scanner.nextLine();
        User user = userService.rechercherUserParNom(nomUtilisateur);
        if (user != null) {
            System.out.println("Utilisateur trouvé : " + user.getNomUtilisateur() + ", Email : " + user.getEmail());
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }

    public void supprimerUser() {
        System.out.print("Entrez le nom d'utilisateur à supprimer : ");
        String nomUtilisateur = scanner.nextLine();
        boolean success = userService.supprimerUser(nomUtilisateur);
        if (success) {
            System.out.println("Utilisateur supprimé avec succès.");
        } else {
            System.out.println("Utilisateur non trouvé, suppression échouée.");
        }
    }
}
