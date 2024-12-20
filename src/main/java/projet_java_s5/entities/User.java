package projet_java_s5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id; // L'ID de l'utilisateur
    private String login;
    private String password;
    private Role role; // Objet Role    
    private String nomUtilisateur; 
    private String email; 

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = new Role(); // Initialisation par défaut
        this.nomUtilisateur = null;
        this.email = null;
    }


    public User(String login, String password, Role role, String nomUtilisateur, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
    }
}
