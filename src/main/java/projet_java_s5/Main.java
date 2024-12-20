package projet_java_s5;

import projet_java_s5.core.bd.DataBaseImpl;
import projet_java_s5.entities.Article;
import projet_java_s5.entities.Client;
import projet_java_s5.entities.Dette;
import projet_java_s5.entities.Paiement;
import projet_java_s5.entities.User;
import projet_java_s5.repositories.bd.ArticleRepositoryBD;
import projet_java_s5.repositories.bd.ClientRepositoryBD;
import projet_java_s5.repositories.bd.DetteRepositoryBD;
import projet_java_s5.repositories.bd.PaiementRepositoryBD;
import projet_java_s5.repositories.bd.UserRepositoryBD;
import projet_java_s5.repositories.list.ArticleRepositoryListImpl;
import projet_java_s5.repositories.list.ClientRepositoryListImpl;
import projet_java_s5.repositories.list.DetteRepositoryListImpl;
import projet_java_s5.repositories.list.PaiementRepositoryListImpl;
import projet_java_s5.repositories.list.UserRepositoryListImpl;
import projet_java_s5.services.Implemente.UserServiceImpl;
import projet_java_s5.views.UserViews;
import projet_java_s5.services.Implemente.ArticleServiceImpl;
import projet_java_s5.services.Implemente.DetteServiceImpl;
import projet_java_s5.services.Implemente.PaiementServiceImpl;
import projet_java_s5.views.ArticleViews;
import projet_java_s5.views.DetteViews;
import projet_java_s5.views.PaiementViews;
import projet_java_s5.services.Implemente.ClientServiceImpl;
import projet_java_s5.views.ClientViews;

import java.util.List;  
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Choix de la méthode de stockage : true pour la base de données, false pour les listes en mémoire
        boolean utiliserBaseDeDonnees = true; // Changez cette valeur pour basculer entre la base de données et la liste

        // Initialisation de la classe DataBaseImpl et connexion
        DataBaseImpl dataBaseImpl = new DataBaseImpl();  // Instanciation de la classe DataBaseImpl
        dataBaseImpl.connect();  // Connexion à la base de données

        // Initialisation des repositories et services en fonction du mode choisi
        ClientRepositoryListImpl clientRepositoryList = new ClientRepositoryListImpl();
        ArticleRepositoryListImpl articleRepositoryList = new ArticleRepositoryListImpl();
        DetteRepositoryListImpl detteRepositoryList = new DetteRepositoryListImpl();
        PaiementRepositoryListImpl paiementRepositoryList = new PaiementRepositoryListImpl();
        UserRepositoryListImpl userRepositoryList = new UserRepositoryListImpl();

ClientRepositoryBD clientRepositoryBD = new ClientRepositoryBD(dataBaseImpl, userRepositoryList);  // Passer dataBaseImpl au constructeur        
ArticleRepositoryBD articleRepositoryBD = new ArticleRepositoryBD(dataBaseImpl);
        DetteRepositoryBD detteRepositoryBD = new DetteRepositoryBD(dataBaseImpl);
        PaiementRepositoryBD paiementRepositoryBD = new PaiementRepositoryBD(dataBaseImpl);
        UserRepositoryBD userRepositoryBD = new UserRepositoryBD(dataBaseImpl);

        // Services selon le mode choisi
        ClientServiceImpl clientService = utiliserBaseDeDonnees ? 
                new ClientServiceImpl(clientRepositoryBD) : new ClientServiceImpl(clientRepositoryList);
        ArticleServiceImpl articleService = utiliserBaseDeDonnees ? 
                new ArticleServiceImpl(articleRepositoryBD) : new ArticleServiceImpl(articleRepositoryList);
        DetteServiceImpl detteService = utiliserBaseDeDonnees ? 
                new DetteServiceImpl(detteRepositoryBD) : new DetteServiceImpl(detteRepositoryList);
        PaiementServiceImpl paiementService = utiliserBaseDeDonnees ? 
                new PaiementServiceImpl(paiementRepositoryBD, detteService) : new PaiementServiceImpl(paiementRepositoryList, detteService);
        UserServiceImpl userService = utiliserBaseDeDonnees ? 
                new UserServiceImpl(userRepositoryBD) : new UserServiceImpl(userRepositoryList);

        // Initialisation des vues
        ClientViews clientViews = new ClientViews(clientService, userService);
        ArticleViews articleViews = new ArticleViews(articleService);
        DetteViews detteViews = new DetteViews(detteService, clientService, articleService);
        PaiementViews paiementViews = new PaiementViews(paiementService, clientService);
        UserViews userViews = new UserViews(userService);

        // Scanner pour les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Connexion de l'utilisateur
        System.out.println("Veuillez vous connecter : ");
        System.out.print("Login : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();
        User currentUser = userService.connecter(login, password);

        // Vérification des identifiants
        if (currentUser == null) {
            System.out.println("Identifiants invalides.");
            return;
        }

        // Vérification si le rôle de l'utilisateur est bien récupéré
        if (currentUser.getRole() == null) {
            System.out.println("Le rôle de l'utilisateur n'a pas été défini correctement.");
            return;
        }

        // Affichage du rôle de l'utilisateur
        System.out.println("\nBienvenue, " + currentUser.getRole().getNom() + "!");

        // Menu principal
        while (true) {
            System.out.println("\nBienvenue, " + currentUser.getRole().getNom() + "!");
            System.out.println("1. Gérer les clients");
            System.out.println("2. Gérer les articles");
            System.out.println("3. Gérer les dettes (Boutiquier)"); // pour le Boutiquier
            System.out.println("4. Gérer les dettes (Client)"); // pour le Client
            System.out.println("5. Gérer les paiements");
            System.out.println("6. Gérer les utilisateurs");
            System.out.println("0. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    if ("Boutiquier".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Créer un client");
                        System.out.println("2. Lister les clients");
                        System.out.println("3. Rechercher un client par téléphone");
                        System.out.println("4. Filtrer les clients avec/sans compte");
                        int choixClient = scanner.nextInt();
                        scanner.nextLine();

                        if (choixClient == 1) {
                            clientViews.creerClient();
                        } else if (choixClient == 2) {
                            clientViews.listerClients();
                        } else if (choixClient == 3) {
                            clientViews.rechercherClientParTelephone();
                        } else if (choixClient == 4) {
                            clientViews.filtrerClientsAvecOuSansCompte();
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Boutiquiers peuvent gérer les clients.");
                    }
                    break;

                case 2:
                    if ("Admin".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Créer un article");
                        System.out.println("2. Lister les articles disponibles");
                        System.out.println("3. Mettre à jour le stock d'un article");
                        int choixArticle = scanner.nextInt();
                        scanner.nextLine();

                        if (choixArticle == 1) {
                            articleViews.creerArticle(); // Appelle la méthode pour créer un article
                        } else if (choixArticle == 2) {
                            articleViews.listerArticles(); // Appelle la méthode pour lister les articles
                        } else if (choixArticle == 3) {
                            articleViews.mettreAJourStock(); // Appelle la méthode pour mettre à jour le stock
                        } else {
                            System.out.println("Choix invalide.");
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Admins peuvent gérer les articles.");
                    }
                    break;

                case 3:
                    if ("Boutiquier".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Créer une dette");
                        System.out.println("2. Modifier une dette");
                        System.out.println("3. Supprimer une dette");
                        int choixDette = scanner.nextInt();
                        scanner.nextLine();

                        if (choixDette == 1) {
                            detteViews.creerDette();
                        } else if (choixDette == 2) {
                            detteViews.modifierDette();
                        } else if (choixDette == 3) {
                            detteViews.supprimerDette();
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Boutiquiers peuvent gérer les dettes.");
                    }
                    break;

                case 4:
                    if ("Client".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Lister les dettes non soldées");
                        System.out.println("2. Filtrer les dettes par état");
                        int choixClientDette = scanner.nextInt();
                        scanner.nextLine();

                        if (choixClientDette == 1) {
                            detteViews.listerDettesNonSoldees();
                        } else if (choixClientDette == 2) {
                            detteViews.filtrerDettesParEtat();
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Clients peuvent gérer les dettes.");
                    }
                    break;

                case 5:
                    if ("Boutiquier".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Enregistrer un paiement");
                        System.out.println("2. Lister tous les paiements");
                        System.out.println("3. Supprimer un paiement");
                        System.out.println("4. Lister les paiements d'un client");
                        int choixPaiement = scanner.nextInt();
                        scanner.nextLine();

                        if (choixPaiement == 1) {
                            paiementViews.enregistrerPaiement();
                        } else if (choixPaiement == 2) {
                            paiementViews.listerTousLesPaiements(); 
                        } else if (choixPaiement == 3) {
                            paiementViews.supprimerPaiement(); 
                        } else if (choixPaiement == 4) {
                            paiementViews.listerPaiementsParClient();
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Boutiquiers peuvent gérer les paiements.");
                    }
                    break;

                case 6:
                    if ("Admin".equals(currentUser.getRole().getNom())) {
                        System.out.println("1. Créer un utilisateur");
                        System.out.println("2. Lister les utilisateurs");
                        System.out.println("3. Rechercher un utilisateur");
                        System.out.println("4. Supprimer un utilisateur");
                        int choixUtilisateur = scanner.nextInt();
                        scanner.nextLine();

                        if (choixUtilisateur == 1) {
                            userViews.ajouterUser();
                        } else if (choixUtilisateur == 2) {
                            userViews.listerUsers();
                        } else if (choixUtilisateur == 3) {
                            userViews.rechercherUser();
                        } else if (choixUtilisateur == 4) {
                            userViews.supprimerUser();
                        }
                    } else {
                        System.out.println("Accès refusé : seuls les Admins peuvent gérer les utilisateurs.");
                    }
                    break;

                case 0:
                    System.out.println("Merci d'avoir utilisé l'application. Au revoir !");
                    return;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
}
