package exam_java.Repositories.Bd;


import exam_java.entities.Professeurs;
import exam_java.Repositories.ProfesseurRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesseursRepositoryBD implements ProfesseurRepository {
    private final DataBase dataBase;

    public ProfesseursRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Professeurs professeur) {
        String sql = "INSERT INTO professeur (nom, prenom) VALUES (?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getPrenom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Professeurs professeur) {
        String sql = "DELETE FROM professeur WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, professeur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Professeurs> findAll() {
        List<Professeurs> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM professeur";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Professeurs professeur = new Professeurs();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

    @Override
    public Optional<Professeurs> findById(Object id) {
        String sql = "SELECT * FROM professeur WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Professeurs professeur = new Professeurs();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                return Optional.of(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
