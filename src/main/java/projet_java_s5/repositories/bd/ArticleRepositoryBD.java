package projet_java_s5.repositories.bd;

import projet_java_s5.entities.Article;
import projet_java_s5.core.bd.DataBase;
import projet_java_s5.repositories.ArticleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryBD implements ArticleRepository {
    private final DataBase dataBase;

    public ArticleRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void ajouterArticle(Article article) {
        String sql = "INSERT INTO article (nom, prix, quantiteEnStock) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, article.getNom());
            stmt.setDouble(2, article.getPrix());
            stmt.setInt(3, article.getQuantiteEnStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> obtenirArticlesDisponibles() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Article article = new Article();
                article.setNom(rs.getString("nom"));
                article.setPrix(rs.getDouble("prix"));
                article.setQuantiteEnStock(rs.getInt("quantiteEnStock"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void mettreAJourStock(Article article, int nouvelleQuantite) {
        String sql = "UPDATE article SET quantiteEnStock = ? WHERE nom = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, nouvelleQuantite);
            stmt.setString(2, article.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
