package projet_java_s5.repositories.bd;

import projet_java_s5.entities.Client;
import projet_java_s5.core.bd.DataBase;
import projet_java_s5.repositories.ClientRepository;
import projet_java_s5.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ClientRepositoryBD.java
public class ClientRepositoryBD implements ClientRepository {
    private final DataBase dataBase;
    private final UserRepository userRepository;  // Ajout de l'injection du UserRepository

    public ClientRepositoryBD(DataBase dataBase, UserRepository userRepository) {
        this.dataBase = dataBase;
        this.userRepository = userRepository;  // Initialisation du UserRepository
    }
    
    @Override
    public void ajouterClient(Client client) {
        String sqlClient = "INSERT INTO client (nom, prenom, telephone, user_id) VALUES (?, ?, ?, ?)";
        Connection connection = dataBase.getConnection();
        try {
            if (client.getUser() != null) {
                String sqlUser = "INSERT INTO utilisateur (login, password, nomUtilisateur, email, role_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, client.getUser().getLogin());
                    stmt.setString(2, client.getUser().getPassword());
                    stmt.setString(3, client.getUser().getNomUtilisateur());
                    stmt.setString(4, client.getUser().getEmail());
    
                    // Récupérer l'ID du rôle en fonction du nom du rôle en appelant getRoleId() depuis UserRepository
                    int roleId = userRepository.getRoleId(client.getUser().getRole().getNom());
                    if (roleId == -1) {
                        System.out.println("Erreur : rôle non trouvé.");
                        return;  // Si le rôle n'est pas trouvé, on arrête l'exécution
                    }
    
                    stmt.setInt(5, roleId);  // Assigner l'ID du rôle
                    stmt.executeUpdate();
    
                    // Récupérer l'ID de l'utilisateur généré
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        // Assigner cet ID à l'utilisateur dans la table client
                        try (PreparedStatement stmtClient = connection.prepareStatement(sqlClient)) {
                            stmtClient.setString(1, client.getNom());
                            stmtClient.setString(2, client.getPrenom());
                            stmtClient.setString(3, client.getTelephone());
                            stmtClient.setInt(4, userId);  // Utiliser l'ID de l'utilisateur
                            stmtClient.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // Si pas de compte utilisateur, juste insérer le client sans associer d'utilisateur
                try (PreparedStatement stmt = connection.prepareStatement(sqlClient)) {
                    stmt.setString(1, client.getNom());
                    stmt.setString(2, client.getPrenom());
                    stmt.setString(3, client.getTelephone());
                    stmt.setNull(4, java.sql.Types.INTEGER); // Aucun utilisateur associé
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public List<Client> obtenirClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setTelephone(rs.getString("telephone"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client rechercherClientParNom(String nom) {
        String sql = "SELECT * FROM client WHERE nom = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setTelephone(rs.getString("telephone"));
                // Associer l'utilisateur si nécessaire
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Client client) {
        String sql = "DELETE FROM client WHERE nom = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, client.getNom());  // Utilisation du nom pour supprimer un client
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
