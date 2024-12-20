package projet_java_s5.services.Implemente;

import projet_java_s5.entities.Client;
import projet_java_s5.repositories.ClientRepository;
import projet_java_s5.services.ClientService;
import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void ajouterClient(Client client) {
        clientRepository.ajouterClient(client);
    }

    @Override
    public List<Client> obtenirClients() {
        return clientRepository.obtenirClients();
    }

    @Override
    public Client rechercherClientParTelephone(String telephone) {
        return clientRepository.obtenirClients().stream()
                .filter(client -> client.getTelephone().equals(telephone))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> filtrerClientsAvecCompte() {
        return clientRepository.obtenirClients().stream()
                .filter(client -> client.getUser() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filtrerClientsSansCompte() {
        return clientRepository.obtenirClients().stream()
                .filter(client -> client.getUser() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List rechercherClientParNom(String nom) {
        return clientRepository.obtenirClients().stream()
                .filter(client -> client.getNom().equalsIgnoreCase(nom))
                .collect(Collectors.toList());
    }
}
