package projet_java_s5.repositories.bd;

import projet_java_s5.entities.Paiement;
import projet_java_s5.entities.Client;
import projet_java_s5.core.bd.DataBase;
import projet_java_s5.repositories.PaiementRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementRepositoryBD implements PaiementRepository {
    private final DataBase dataBase;

    public PaiementRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        String sql = "INSERT INTO paiement (montant, client_id, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setDouble(1, paiement.getMontant());
            stmt.setInt(2, paiement.getClient().getId());  // assuming Client has an ID
            stmt.setDate(3, new java.sql.Date(paiement.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paiement> listerTousLesPaiements() {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(rs.getInt("id"));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setDate(rs.getDate("date"));
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }

    @Override
    public Paiement rechercherPaiementParId(int id) {
        String sql = "SELECT * FROM paiement WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(rs.getInt("id"));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setDate(rs.getDate("date"));
                return paiement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerPaiement(int id) {
        String sql = "DELETE FROM paiement WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paiement> listerPaiementsParClient(Client client) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE client_id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, client.getId());  // assuming Client has an ID
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(rs.getInt("id"));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setDate(rs.getDate("date"));
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }
}
