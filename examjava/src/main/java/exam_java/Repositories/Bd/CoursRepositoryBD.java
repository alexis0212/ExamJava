package exam_java.Repositories.Bd;

import exam_java.entities.Cours;
import exam_java.Repositories.CoursRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursRepositoryBD implements CoursRepository {
    private final DataBase dataBase;

    public CoursRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Cours cours) {
        String sql = "INSERT INTO cours (professeur_id, classe_id, module_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, cours.getProfesseur().getId());
            stmt.setInt(2, cours.getClasses().getId());
            stmt.setInt(3, cours.getModule().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cours cours) {
        String sql = "DELETE FROM cours WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, cours.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String sql = "SELECT * FROM cours";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cours cours = new Cours();
                cours.setId(rs.getInt("id"));
                // Mapping des autres attributs...
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }

    @Override
    public Optional<Cours> findById(Object id) {
        String sql = "SELECT * FROM cours WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cours cours = new Cours();
                cours.setId(rs.getInt("id"));
                // Mapping des autres attributs...
                return Optional.of(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
