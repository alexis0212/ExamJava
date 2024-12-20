package exam_java.core.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseImpl implements DataBase {
    // Paramètres pour la connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/examjava";
    private static final String USER = "root"; // Nom d'utilisateur
    private static final String PASSWORD = ""; // Mot de passe

    private Connection connection;

    @Override
    public void connect() {
        try {
            // Essaie de se connecter à la base de données
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie : " + URL);
            }
        } catch (SQLException e) {
            // Gestion des erreurs de connexion
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Déconnexion réussie.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la déconnexion : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la connexion : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
