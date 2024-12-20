package projet_java_s5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dette {
    private double montant;
    private double montantVerser;
    private double montantRestant;
    private String etat;
    private boolean soldee;
    private Client client;
    private Date date;
    private List<Article> articles; // Liste des articles associés
}
