package projet_java_s5.repositories.list;

import projet_java_s5.entities.Role;
import projet_java_s5.entities.User;
import projet_java_s5.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryListImpl extends RepositoryListImpl<User> implements UserRepository {

    public UserRepositoryListImpl() {
        // Initialisation de la liste d'utilisateurs avec des utilisateurs par défaut
        // Utilise 'data' au lieu de 'users' car c'est la variable héritée de RepositoryListImpl
        data = new ArrayList<>();
        
        // Ajout d'utilisateurs par défaut avec des rôles
        Role roleBoutiquier = new Role("Boutiquier");
        User user1 = new User("boutiquier", "motdepasse1", roleBoutiquier, "Boutiquier Utilisateur", "boutiquier@mail.com");
        data.add(user1);

        Role roleAdmin = new Role("Admin");
        User user2 = new User("admin", "motdepasse2", roleAdmin, "Admin Utilisateur", "admin@mail.com");
        data.add(user2);

        Role roleClient = new Role("Client");
        User user3 = new User("client", "motdepasse3", roleClient, "Client Utilisateur", "client@mail.com");
        data.add(user3); // Ajout d'un utilisateur avec le rôle Client
    }

    @Override
public int getRoleId(String roleName) {
    // Recherche d'un rôle dans la liste d'utilisateurs
    return data.stream()
            .map(User::getRole)  // Récupère le rôle de chaque utilisateur
            .filter(role -> role.getNom().equals(roleName))  // Filtre les rôles avec le nom correspondant
            .mapToInt(role -> {
                // Retourne l'ID du rôle
                // Vous devez avoir un identifiant associé au rôle dans votre modèle
                // Si Role n'a pas d'ID, vous pouvez soit en ajouter un, soit renvoyer un code spécifique
                return role.getId();  // Assurez-vous que Role a une méthode getId() ou modifiez la logique
            })
            .findFirst()
            .orElse(-1);  // Retourne -1 si aucun rôle correspondant n'est trouvé
}

    @Override
    public Optional<User> findById(Object login) {
        // Recherche un utilisateur par son login (identifiant unique)
        return data.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst(); // Trouve le premier utilisateur correspondant
    }

    @Override
    public void save(User user) {
        // Supprime l'utilisateur existant avec le même login (si présent) et ajoute le nouvel utilisateur
        data.removeIf(u -> u.getLogin().equals(user.getLogin()));
        data.add(user);
    }

    @Override
    public User authentifierUser(String login, String motDePasse) {
        // Recherche un utilisateur par login et mot de passe
        return data.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(motDePasse))
                .findFirst()
                .orElse(null); // Retourne null si aucun utilisateur correspondant n'est trouvé
    }

    @Override
    public Optional<User> findByNomUtilisateur(String nomUtilisateur) {
        // Recherche un utilisateur par son nom d'utilisateur
        return data.stream()
                .filter(user -> user.getNomUtilisateur() != null && user.getNomUtilisateur().equals(nomUtilisateur))
                .findFirst();
    }
}
