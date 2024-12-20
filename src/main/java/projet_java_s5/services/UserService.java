package projet_java_s5.services;

import projet_java_s5.entities.User;

import java.util.List;

public interface UserService {
    void creerUser(User user); // Créer un utilisateur
    User rechercherUserParNom(String nomUtilisateur); // Rechercher un utilisateur par nom
    boolean supprimerUser(String nomUtilisateur); // Supprimer un utilisateur par nom
    List<User> obtenirUsers(); // Obtenir tous les utilisateurs
}
