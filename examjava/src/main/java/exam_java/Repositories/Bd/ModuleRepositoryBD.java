package exam_java.Repositories.Bd;


import exam_java.entities.Module;
import exam_java.Repositories.ModuleRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModuleRepositoryBD implements ModuleRepository {
    private final DataBase dataBase;

    public ModuleRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Module module) {
        String sql = "INSERT INTO module (nom) VALUES (?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, module.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Module module) {
        String sql = "DELETE FROM module WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, module.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Module> findAll() {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT * FROM module";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    @Override
    public Optional<Module> findById(Object id) {
        String sql = "SELECT * FROM module WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                return Optional.of(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

