package projet_java_s5.core.bd;

public interface DataBase {
    void connect();
    void disconnect();
    boolean isConnected();
    java.sql.Connection getConnection();
}
