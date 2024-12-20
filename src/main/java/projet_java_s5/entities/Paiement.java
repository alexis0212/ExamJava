package projet_java_s5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    private int id;
    private double montant;
    private Client client;  // Association avec le client
    private Date date;      // Date du paiement
}
