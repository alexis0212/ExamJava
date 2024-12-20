package projet_java_s5.repositories.bd;

import projet_java_s5.entities.Dette;
import projet_java_s5.entities.Client;
import projet_java_s5.core.bd.DataBase;
import projet_java_s5.repositories.DetteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetteRepositoryBD implements DetteRepository {
    private final DataBase dataBase;

    public DetteRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void ajouterDette(Dette dette) {
        String sql = "INSERT INTO dette (montant, montantVerser, montantRestant, etat, soldee, client_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setDouble(1, dette.getMontant());
            stmt.setDouble(2, dette.getMontantVerser());
            stmt.setDouble(3, dette.getMontantRestant());
            stmt.setString(4, dette.getEtat());
            stmt.setBoolean(5, dette.isSoldee());
            stmt.setInt(6, dette.getClient().getId());  // assuming Client has an ID
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dette> obtenirDettesNonSoldees() {
        List<Dette> dettes = new ArrayList<>();
        String sql = "SELECT * FROM dette WHERE soldee = FALSE";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dette dette = new Dette();
                dette.setMontant(rs.getDouble("montant"));
                dette.setMontantVerser(rs.getDouble("montantVerser"));
                dette.setMontantRestant(rs.getDouble("montantRestant"));
                dette.setEtat(rs.getString("etat"));
                dette.setSoldee(rs.getBoolean("soldee"));
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettes;
    }

    @Override
    public List<Dette> filtrerDettesParEtat(String etat) {
        List<Dette> dettes = new ArrayList<>();
        String sql = "SELECT * FROM dette WHERE etat = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, etat);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dette dette = new Dette();
                dette.setMontant(rs.getDouble("montant"));
                dette.setMontantVerser(rs.getDouble("montantVerser"));
                dette.setMontantRestant(rs.getDouble("montantRestant"));
                dette.setEtat(rs.getString("etat"));
                dette.setSoldee(rs.getBoolean("soldee"));
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettes;
    }

    @Override
    public List<Dette> obtenirDettesSoldees() {
        List<Dette> dettes = new ArrayList<>();
        String sql = "SELECT * FROM dette WHERE soldee = TRUE";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dette dette = new Dette();
                dette.setMontant(rs.getDouble("montant"));
                dette.setMontantVerser(rs.getDouble("montantVerser"));
                dette.setMontantRestant(rs.getDouble("montantRestant"));
                dette.setEtat(rs.getString("etat"));
                dette.setSoldee(rs.getBoolean("soldee"));
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettes;
    }

    @Override
    public void archiverDettesSoldees(List<Dette> dettesSoldees) {
        String sql = "DELETE FROM dette WHERE soldee = TRUE";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            for (Dette dette : dettesSoldees) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierDette(Client client, double nouveauMontant, String nouvelEtat) {
        String sql = "UPDATE dette SET montant = ?, etat = ? WHERE client_id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setDouble(1, nouveauMontant);
            stmt.setString(2, nouvelEtat);
            stmt.setInt(3, client.getId());  // assuming Client has an ID
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerDette(Client client) {
        String sql = "DELETE FROM dette WHERE client_id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, client.getId());  // assuming Client has an ID
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
