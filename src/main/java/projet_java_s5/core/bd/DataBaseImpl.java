package projet_java_s5.core.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseImpl implements DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/gescahierdettejava";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    @Override
    public void connect() {
        try {
            // Essaie d'établir une connexion à la base de données
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie.");
            }
        } catch (SQLException e) {
            // Si une exception se produit, affiche les détails
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
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
            System.out.println("Erreur de déconnexion : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de la connexion : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
