package projet_java_s5.repositories;

import projet_java_s5.entities.Client;
import java.util.List;

public interface ClientRepository {
    void ajouterClient(Client client);
    List<Client> obtenirClients();
    Client rechercherClientParNom(String nom);
    void delete(Client client);  // Ajout de la méthode delete pour supprimer un client
}
