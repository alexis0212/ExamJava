package projet_java_s5.repositories.bd;

import projet_java_s5.entities.Role;
import projet_java_s5.entities.User;
import projet_java_s5.core.bd.DataBase;
import projet_java_s5.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryBD implements UserRepository {
    private final DataBase dataBase;

    public UserRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    @Override
    public Optional<User> findByNomUtilisateur(String nomUtilisateur) {
        String sql = "SELECT u.*, r.nom AS role_nom FROM utilisateur u " +
                     "JOIN role r ON u.role_id = r.id WHERE u.login = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomUtilisateur);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setNomUtilisateur(rs.getString("nomUtilisateur"));
                user.setEmail(rs.getString("email"));
    
                // Création et association du rôle
                Role role = new Role();
                role.setNom(rs.getString("role_nom"));  // Récupère le nom du rôle
                user.setRole(role); // Associe le rôle à l'utilisateur
    
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    
    
    @Override
    public void save(User user) {
        String sql = "INSERT INTO utilisateur (login, password, nomUtilisateur, email, role_id) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            // Récupération de l'ID du rôle
            int roleId = getRoleId(user.getRole().getNom());  // Récupérer l'ID du rôle en fonction de son nom
    
            // Insertion de l'utilisateur dans la table
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNomUtilisateur());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, roleId);  // Utiliser l'ID du rôle récupéré
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
public int getRoleId(String roleName) {
    String sql = "SELECT id FROM role WHERE nom = ?";
    try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
        stmt.setString(1, roleName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int roleId = rs.getInt("id");
            System.out.println("Role ID for " + roleName + ": " + roleId);  // Debug print
            return roleId;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;  // Retourner -1 si le rôle n'est pas trouvé
}

    
    
    @Override
    public void delete(User user) {
        String sql = "DELETE FROM utilisateur WHERE login = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try (Statement stmt = dataBase.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setNomUtilisateur(rs.getString("nomUtilisateur"));
                user.setEmail(rs.getString("email"));
                // Associer le rôle avec l'ID ou la logique de rôle
                // user.setRole(role); // à implémenter en fonction de la gestion des rôles
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User authentifierUser(String login, String motDePasse) {
        String sql = "SELECT u.*, r.nom AS role_nom FROM utilisateur u " +
                     "JOIN role r ON u.role_id = r.id WHERE u.login = ? AND u.password = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setNomUtilisateur(rs.getString("nomUtilisateur"));
                user.setEmail(rs.getString("email"));
    
                // Création et association du rôle
                Role role = new Role();
                role.setNom(rs.getString("role_nom"));  // Récupère le nom du rôle
                user.setRole(role); // Associe le rôle à l'utilisateur
    
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Si l'authentification échoue
    }
    

}
