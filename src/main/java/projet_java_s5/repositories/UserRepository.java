package projet_java_s5.repositories;

import projet_java_s5.entities.User;

import java.util.List;
import java.util.Optional;

// UserRepository.java
public interface UserRepository {
    void save(User user); 
    void delete(User user); 
    Optional<User> findByNomUtilisateur(String nomUtilisateur); 
    List<User> findAll(); 
    User authentifierUser(String login, String motDePasse); 
    int getRoleId(String roleName);  // Déclaration de la méthode getRoleId
}
