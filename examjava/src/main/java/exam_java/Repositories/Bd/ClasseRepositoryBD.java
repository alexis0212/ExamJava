package exam_java.Repositories.Bd;


import exam_java.entities.Classe;
import exam_java.entities.Niveau;
import exam_java.Repositories.ClasseRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClasseRepositoryBD implements ClasseRepository {
    private final DataBase dataBase;

    public ClasseRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Classe classe) {
        String sql = "INSERT INTO classe (nom, niveau_id) VALUES (?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, classe.getNom());
            stmt.setInt(2, classe.getNiveau().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Classe classe) {
        String sql = "DELETE FROM classe WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, classe.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Classe> findAll() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classes.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public Optional<Classe> findById(Object id) {
        String sql = "SELECT * FROM classe WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                return Optional.of(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
