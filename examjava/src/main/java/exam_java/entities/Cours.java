package exam_java.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cours {
    private int id; // Identifiant unique
    private Professeurs professeur; // Professeur associé au cours
    private Classe classes; // Classe associée au cours
    private Module module; // Module associé au cours
}
