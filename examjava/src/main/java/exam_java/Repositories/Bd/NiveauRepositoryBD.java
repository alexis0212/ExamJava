package exam_java.Repositories.Bd;


import exam_java.entities.Niveau;
import exam_java.Repositories.NiveauRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NiveauRepositoryBD implements NiveauRepository {
    private final DataBase dataBase;

    public NiveauRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Niveau niveau) {
        String sql = "INSERT INTO niveau (nom) VALUES (?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, niveau.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Niveau niveau) {
        String sql = "DELETE FROM niveau WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, niveau.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Niveau> findAll() {
        List<Niveau> niveaux = new ArrayList<>();
        String sql = "SELECT * FROM niveau";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Niveau niveau = new Niveau();
                niveau.setId(rs.getInt("id"));
                niveau.setNom(rs.getString("nom"));
                niveaux.add(niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveaux;
    }

    @Override
    public Optional<Niveau> findById(Object id) {
        String sql = "SELECT * FROM niveau WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Niveau niveau = new Niveau();
                niveau.setId(rs.getInt("id"));
                niveau.setNom(rs.getString("nom"));
                return Optional.of(niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

