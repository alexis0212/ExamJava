package projet_java_s5.views;

import projet_java_s5.entities.Article;
import projet_java_s5.services.Implemente.ArticleServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArticleViews {
    private ArticleServiceImpl articleService;
    private Scanner scanner;

    public ArticleViews(ArticleServiceImpl articleService) {
        this.articleService = articleService;
        this.scanner = new Scanner(System.in);
    }

    public void creerArticle() {
        Article article = new Article();
        System.out.print("Entrez le nom de l'article : ");
        article.setNom(scanner.nextLine());

        try {
            System.out.print("Entrez le prix de l'article : ");
            article.setPrix(scanner.nextDouble());

            System.out.print("Entrez la quantité en stock : ");
            article.setQuantiteEnStock(scanner.nextInt());

            articleService.ajouterArticle(article);
            System.out.println("Article créé avec succès.");
        } catch (InputMismatchException e) {
            System.out.println("Erreur : veuillez entrer un nombre valide pour le prix et la quantité.");
            scanner.nextLine(); // Vider le tampon pour éviter une boucle infinie
        }
    }

    public void listerArticles() {
        System.out.println("Articles disponibles : ");
        articleService.obtenirArticlesDisponibles().forEach(a -> {
            System.out.println("Nom: " + a.getNom() + ", Prix: " + a.getPrix() + ", Quantité: " + a.getQuantiteEnStock());
        });
    }

    public void mettreAJourStock() {
        System.out.print("Entrez le nom de l'article à mettre à jour : ");
        String nomArticle = scanner.nextLine();
        Article articleToUpdate = articleService.obtenirArticleParNom(nomArticle);
        if (articleToUpdate != null) {
            try {
                System.out.print("Entrez la nouvelle quantité : ");
                int nouvelleQuantite = scanner.nextInt();
                articleService.mettreAJourStock(nomArticle, nouvelleQuantite);
                System.out.println("Stock mis à jour avec succès.");
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez entrer un nombre valide pour la quantité.");
                scanner.nextLine(); // Vider le tampon
            }
        } else {
            System.out.println("Article non trouvé.");
        }
    }
}
