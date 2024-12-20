package projet_java_s5.views;

import projet_java_s5.entities.Dette;
import projet_java_s5.entities.Article;
import projet_java_s5.entities.Client;
import projet_java_s5.services.Implemente.DetteServiceImpl;
import projet_java_s5.services.ArticleService;
import projet_java_s5.services.Implemente.ClientServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DetteViews {
    private final DetteServiceImpl detteService;
    private final ClientServiceImpl clientService;
    private final ArticleService articleService; // Ajoutez ceci
    private final Scanner scanner;

    public DetteViews(DetteServiceImpl detteService, ClientServiceImpl clientService, ArticleService articleService) {        this.detteService = detteService;
        this.clientService = clientService;
        this.articleService = articleService;
        this.scanner = new Scanner(System.in);
    }

    public void creerDette() {
        System.out.print("Entrez le montant de la dette : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Entrez l'état de la dette : ");
        String etat = scanner.nextLine();

        System.out.print("Entrez le nom du client : ");
        String nomClient = scanner.nextLine();
        List<Client> clients = clientService.rechercherClientParNom(nomClient);

        if (clients.isEmpty()) {
            System.out.println("Client non trouvé, la dette ne sera pas créée.");
        } else {
            Client client = clients.get(0);

            // Choisir les articles pour la dette
            List<Article> articles = new ArrayList<>();
            String continuer;
            do {
                System.out.print("Entrez le nom de l'article à ajouter à la dette : ");
                String nomArticle = scanner.nextLine();

                Article article = articleService.obtenirArticleParNom(nomArticle); // Utilisez articleService ici
                if (article != null) {
                    articles.add(article);
                    System.out.println("Article ajouté.");
                } else {
                    System.out.println("Article non trouvé.");
                }

                System.out.print("Voulez-vous ajouter un autre article ? (oui/non) : ");
                continuer = scanner.nextLine();
            } while (continuer.equalsIgnoreCase("oui"));

            Dette dette = new Dette(montant, 0, montant, etat, false, client, new Date(), articles);
            detteService.ajouterDette(dette);
            System.out.println("Dette créée avec succès.");
        }
    }


    public void modifierDette() {
        System.out.print("Entrez le nom du client pour modifier sa dette : ");
        String nomClient = scanner.nextLine();
        List<Client> clients = clientService.rechercherClientParNom(nomClient);

        if (clients.isEmpty()) {
            System.out.println("Client non trouvé.");
        } else {
            Client client = clients.get(0);
            System.out.print("Entrez le nouveau montant de la dette : ");
            double nouveauMontant = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Entrez le nouvel état de la dette : ");
            String nouvelEtat = scanner.nextLine();

            detteService.modifierDette(client, nouveauMontant, nouvelEtat);
        }
    }

    public void supprimerDette() {
        System.out.print("Entrez le nom du client pour supprimer sa dette : ");
        String nomClient = scanner.nextLine();
        List<Client> clients = clientService.rechercherClientParNom(nomClient);

        if (clients.isEmpty()) {
            System.out.println("Client non trouvé.");
        } else {
            Client client = clients.get(0);
            detteService.supprimerDette(client);
        }
    }

    public void listerDettesNonSoldees() {
        System.out.println("Dettes non soldées : ");
        List<Dette> dettes = detteService.obtenirDettesNonSoldees();
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette non soldée.");
        } else {
            dettes.forEach(d -> {
                System.out.println("Montant: " + d.getMontant() + ", État: " + d.getEtat() + ", Date: " + d.getDate());
            });
        }
    }

    public void filtrerDettesParEtat() {
        System.out.print("Entrez l'état à filtrer : ");
        String etat = scanner.nextLine();
        List<Dette> dettesFiltres = detteService.filtrerDettesParEtat(etat);
        if (dettesFiltres.isEmpty()) {
            System.out.println("Aucune dette trouvée avec cet état.");
        } else {
            dettesFiltres.forEach(d -> {
                System.out.println("Montant: " + d.getMontant() + ", État: " + d.getEtat() + ", Date: " + d.getDate());
            });
        }
    }
}
