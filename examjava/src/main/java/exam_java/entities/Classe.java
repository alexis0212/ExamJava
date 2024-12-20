package exam_java.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    private int id; // Identifiant unique
    private String nom; // Nom de la classe (ex. "Terminale S1")
    private Niveau niveau; // Niveau auquel appartient la classe
}

