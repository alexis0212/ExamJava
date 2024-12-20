package projet_java_s5.services;

import projet_java_s5.entities.Client;
import java.util.List;

public interface ClientService {
    void ajouterClient(Client client);
    List<Client> obtenirClients();
    Client rechercherClientParTelephone(String telephone);
    List<Client> filtrerClientsAvecCompte();
    List<Client> filtrerClientsSansCompte();
    List<Client> rechercherClientParNom(String nom);
}
