package projet_java_s5.repositories;

import projet_java_s5.entities.Article;
import java.util.List;

public interface ArticleRepository {
    void ajouterArticle(Article article);
    List<Article> obtenirArticlesDisponibles();
    void mettreAJourStock(Article article, int nouvelleQuantite);
}
