package projet_java_s5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id; // L'ID de l'utilisateur
    private String nom; // e.g., "Admin", "Boutiquier"

    public Role(String roleId) {
        this.nom = roleId;
    }

    
}
