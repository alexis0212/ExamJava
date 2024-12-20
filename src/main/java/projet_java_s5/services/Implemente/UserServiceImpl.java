package projet_java_s5.services.Implemente;

import projet_java_s5.entities.User;
import projet_java_s5.services.UserService;
import projet_java_s5.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void creerUser(User user) {
        userRepository.save(user); 
    }

    @Override
    public User rechercherUserParNom(String nomUtilisateur) {
        return userRepository.findByNomUtilisateur(nomUtilisateur).orElse(null); 
    }

    @Override
    public boolean supprimerUser(String nomUtilisateur) {
        User user = rechercherUserParNom(nomUtilisateur);
        if (user != null) {
            userRepository.delete(user); 
            return true;
        }
        return false; 
    }

    @Override
    public List<User> obtenirUsers() {
        return userRepository.findAll(); 
    }

    public User connecter(String login, String motDePasse) {
        return userRepository.authentifierUser(login, motDePasse);
    }
}
