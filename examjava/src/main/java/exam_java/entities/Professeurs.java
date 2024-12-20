package exam_java.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professeurs {
    private int id; 
    private String nom; 
    private String prenom; 
}

