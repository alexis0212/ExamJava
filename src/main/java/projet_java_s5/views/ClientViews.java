package projet_java_s5.views;

import projet_java_s5.entities.Client;
import projet_java_s5.entities.Role;
import projet_java_s5.entities.User;
import projet_java_s5.services.Implemente.ClientServiceImpl;
import projet_java_s5.services.Implemente.UserServiceImpl; // Ajout de UserService

import java.util.Scanner;

public class ClientViews {
    private ClientServiceImpl clientService;
    private UserServiceImpl userService; // Service pour ajouter l'utilisateur
    private Scanner scanner;

    public ClientViews(ClientServiceImpl clientService, UserServiceImpl userService) {
        this.clientService = clientService;
        this.userService = userService; // Initialisation du service
        this.scanner = new Scanner(System.in);
    }

    public void creerClient() {
        System.out.print("Entrez le nom du client : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom du client : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez le téléphone du client : ");
        String telephone = scanner.nextLine();
        
        User user = null; // Initialiser l'utilisateur à null
    
        System.out.print("Souhaitez-vous créer un compte utilisateur pour ce client ? (oui/non) : ");
        String reponse = scanner.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            user = creerUtilisateur();  // Appel de la méthode pour créer un utilisateur si nécessaire
        }
    
        // Créer le client sans l'utilisateur si nécessaire
        Client client = new Client(nom, prenom, telephone, user);
        clientService.ajouterClient(client);
        System.out.println("Client créé avec succès.");
    }
    
   private User creerUtilisateur() {
    System.out.print("Entrez le login : ");
    String login = scanner.nextLine();
    System.out.print("Entrez le mot de passe : ");
    String password = scanner.nextLine();

    // Demander le nom d'utilisateur et l'email
    System.out.print("Entrez le nom d'utilisateur : ");
    String nomUtilisateur = scanner.nextLine();
    System.out.print("Entrez l'email : ");
    String email = scanner.nextLine();

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

    // Créer un utilisateur avec les informations fournies
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setNomUtilisateur(nomUtilisateur);  // Ajouter le nom d'utilisateur
    user.setEmail(email);  // Ajouter l'email
    user.setRole(role);  // Assigner le rôle choisi

    return user;
}

    

    public void listerClients() {
        System.out.println("Liste des clients : ");
        clientService.obtenirClients().forEach(c -> {
            System.out.println("Nom: " + c.getNom() + ", Prénom: " + c.getPrenom() + ", Téléphone: " + c.getTelephone());
        });
    }

    public void rechercherClientParTelephone() {
        System.out.print("Entrez le téléphone du client à rechercher : ");
        String telephone = scanner.nextLine();
        Client client = clientService.rechercherClientParTelephone(telephone);
        if (client != null) {
            System.out.println("Client trouvé : " + client.getNom() + " " + client.getPrenom());
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    public void filtrerClientsAvecOuSansCompte() {
        System.out.println("1. Avec compte");
        System.out.println("2. Sans compte");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            clientService.filtrerClientsAvecCompte().forEach(c -> {
                System.out.println("Client avec compte : " + c.getNom() + " " + c.getPrenom());
            });
        } else {
            clientService.filtrerClientsSansCompte().forEach(c -> {
                System.out.println("Client sans compte : " + c.getNom() + " " + c.getPrenom());
            });
        }
    }
}
