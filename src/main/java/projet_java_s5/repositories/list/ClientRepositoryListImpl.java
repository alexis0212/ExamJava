package projet_java_s5.repositories.list;

import projet_java_s5.entities.Client;
import projet_java_s5.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryListImpl extends RepositoryListImpl<Client> implements ClientRepository {

    @Override
    public Optional<Client> findById(Object nom) {
        return data.stream()
                .filter(client -> client.getNom().equalsIgnoreCase((String) nom))
                .findFirst();
    }

    @Override
    public void ajouterClient(Client client) {
        save(client);
    }

    @Override
    public List<Client> obtenirClients() {
        return findAll();
    }

    @Override
    public Client rechercherClientParNom(String nom) {
        return findById(nom).orElse(null);
    }
}
