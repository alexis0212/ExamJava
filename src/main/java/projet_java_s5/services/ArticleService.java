package projet_java_s5.services;

import projet_java_s5.entities.Article;
import java.util.List;

public interface ArticleService {
    void ajouterArticle(Article article);
    List<Article> obtenirArticlesDisponibles();
    void mettreAJourStock(String nomArticle, int nouvelleQuantite);
    Article obtenirArticleParNom(String nomArticle); // Méthode ajoutée
}
