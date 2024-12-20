package exam_java.core.bd;

import java.sql.Connection;

public interface DataBase {
    void connect(); // Établit la connexion à la base de données.
    void disconnect(); // Ferme la connexion.
    boolean isConnected(); // Vérifie si la connexion est active.
    Connection getConnection(); // Retourne l'objet de connexion JDBC.
}
