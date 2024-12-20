package projet_java_s5.repositories.list;

import projet_java_s5.entities.Article;
import projet_java_s5.repositories.ArticleRepository;

import java.util.List;
import java.util.Optional;

public class ArticleRepositoryListImpl extends RepositoryListImpl<Article> implements ArticleRepository {

    @Override
    public Optional<Article> findById(Object nom) {
        return data.stream()
                .filter(article -> article.getNom().equals(nom))
                .findFirst();
    }

    @Override
    public void ajouterArticle(Article article) {
        if (findById(article.getNom()).isPresent()) {
            System.out.println("L'article existe déjà.");
        } else {
            save(article);
        }
    }

    @Override
    public List<Article> obtenirArticlesDisponibles() {
        return findAll();
    }

    @Override
    public void mettreAJourStock(Article articleToUpdate, int nouvelleQuantite) {
        findById(articleToUpdate.getNom()).ifPresentOrElse(
            article -> article.setQuantiteEnStock(nouvelleQuantite),
            () -> System.out.println("Article non trouvé pour la mise à jour.")
        );
    }
}
