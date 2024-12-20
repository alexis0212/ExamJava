package projet_java_s5.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private int id;// L'ID de l'utilisateur
    private String nom;
    private String prenom;
    private String telephone;
    private User user;  // Association avec l'utilisateur

    public Client(String nom, String prenom, String telephone, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.user = user;  // Associer l'utilisateur
    }
}
