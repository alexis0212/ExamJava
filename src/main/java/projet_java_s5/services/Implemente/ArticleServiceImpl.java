package projet_java_s5.services.Implemente;

import projet_java_s5.entities.Article;
import projet_java_s5.repositories.ArticleRepository;
import projet_java_s5.services.ArticleService;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    // Le constructeur accepte un ArticleRepository générique (soit en mémoire, soit en base de données)
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void ajouterArticle(Article article) {
        articleRepository.ajouterArticle(article);
    }

    @Override
    public List<Article> obtenirArticlesDisponibles() {
        return articleRepository.obtenirArticlesDisponibles();
    }

    @Override
    public void mettreAJourStock(String nomArticle, int nouvelleQuantite) {
        Article article = obtenirArticleParNom(nomArticle);
        if (article != null) {
            articleRepository.mettreAJourStock(article, nouvelleQuantite);
        } else {
            System.out.println("Article non trouvé pour la mise à jour.");
        }
    }

    @Override
    public Article obtenirArticleParNom(String nomArticle) {
        return articleRepository.obtenirArticlesDisponibles()
                                .stream()
                                .filter(a -> a.getNom().equals(nomArticle))
                                .findFirst()
                                .orElse(null);
    }
}
